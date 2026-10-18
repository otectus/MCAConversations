#!/usr/bin/env python3
"""Boot the packaged Forge 1.20.1 dedicated server with an explicit mod list, for MCA: Conversations checks.

The vehicle for every dedicated-server check in docs/MCAConversations-Feedback-Remediation-Implementation-Plan.md
(section 5, parts C and D). Its launch sequence is copied from MCAKingdoms/tools/test/production_runtime.py, which is
read, never modified: libraries are symlinked from the packaged server, the world is a fresh flat world or a copy
of --seed-world (never the original), and every run gets its own new directory.

Example:
  python3 tools/test/conversations_server.py \
      --mod build/libs/mcaconversations-1.8.0.jar \
      --mod "/home/otectus/Documents/curseforge/minecraft/Instances/Ultima/mods/minecraft-comes-alive-7.6.26+1.20.1-universal.jar" \
      --mod "/home/otectus/Documents/curseforge/minecraft/Instances/Ultima/mods/architectury-9.2.14-forge.jar" \
      --command "conversations perf status" --wait-ticks 1300 --command "conversations perf status"

Writes <work-dir>/artifacts.sha256 (every mod jar, plus the seed world's level.dat when one is copied), the server
log as <work-dir>/server.log, and prints PASS/FAIL lines. Exit status is non-zero when the server does not start,
does not stop cleanly, or logs an exception mentioning mcaconversations.
"""
import argparse
import hashlib
import pathlib
import shutil
import subprocess
import sys
import time

FORGE_ROOT = pathlib.Path('/home/otectus/Projects/MCAKingdoms/build/forge-server')
FORGE_ARGS = '@libraries/net/minecraftforge/forge/1.20.1-47.4.23/unix_args.txt'
EULA_SOURCES = [pathlib.Path('/home/otectus/Projects/MCACrime/run/eula.txt')]
FLAT = ('{"layers":[{"block":"minecraft:bedrock","height":1},{"block":"minecraft:dirt","height":2},'
        '{"block":"minecraft:grass_block","height":1}],"biome":"minecraft:plains"}')


def sha256(path):
    digest = hashlib.sha256()
    with open(path, 'rb') as handle:
        for block in iter(lambda: handle.read(1 << 20), b''):
            digest.update(block)
    return digest.hexdigest()


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument('--mod', type=pathlib.Path, action='append', default=[], required=True,
                        help='a mod jar to install (repeatable)')
    parser.add_argument('--seed-world', type=pathlib.Path, help='a world directory to copy as the run world')
    parser.add_argument('--view-distance', type=int, default=4)
    parser.add_argument('--simulation-distance', type=int, default=4)
    parser.add_argument('--work-dir', type=pathlib.Path,
                        help='new run directory (default build/server-runs/<timestamp>)')
    parser.add_argument('--forge-root', type=pathlib.Path, default=FORGE_ROOT)
    parser.add_argument('--command', action='append', default=[],
                        help='a console command sent after start, in order (repeatable)')
    parser.add_argument('--wait-ticks', type=int, action='append', default=[],
                        help='seconds/20 to wait after the command at the same position (repeatable)')
    parser.add_argument('--xmx', default='2G')
    parser.add_argument('--accept-eula', action='store_true',
                        help='write eula=true when no accepted eula.txt is found to copy')
    args = parser.parse_args()

    root = pathlib.Path(__file__).resolve().parents[2]
    work = (args.work_dir or root / 'build' / 'server-runs' / time.strftime('%Y%m%d-%H%M%S')).resolve()
    if work.exists():
        raise SystemExit(f'Refusing to reuse runtime directory {work}')
    libraries = args.forge_root / 'libraries'
    if not libraries.is_dir():
        raise SystemExit(f'No packaged Forge server at {args.forge_root}')
    work.mkdir(parents=True)
    (work / 'libraries').symlink_to(libraries.resolve(), target_is_directory=True)
    mods = work / 'mods'
    mods.mkdir()
    manifest = []
    for jar in args.mod:
        jar = jar.resolve()
        if not jar.is_file():
            raise SystemExit(f'No such mod jar: {jar}')
        shutil.copy2(jar, mods / jar.name)
        manifest.append(f'{sha256(jar)}  mods/{jar.name}')
    if args.seed_world:
        seed = args.seed_world.resolve()
        shutil.copytree(seed, work / 'world')
        level = seed / 'level.dat'
        if level.is_file():
            manifest.append(f'{sha256(level)}  seed-world/level.dat ({seed})')
    (work / 'artifacts.sha256').write_text('\n'.join(manifest) + '\n')

    eula = next((source for source in EULA_SOURCES if source.is_file()), None)
    if eula is not None:
        shutil.copy2(eula, work / 'eula.txt')
    elif args.accept_eula:
        (work / 'eula.txt').write_text('eula=true\n')
    else:
        raise SystemExit('No accepted eula.txt to copy; pass --accept-eula to accept the Minecraft EULA.')
    (work / 'server.properties').write_text(
        'online-mode=false\nserver-ip=127.0.0.1\nserver-port=0\nlevel-type=minecraft:flat\n'
        'generate-structures=false\nspawn-protection=0\n'
        f'view-distance={args.view_distance}\nsimulation-distance={args.simulation_distance}\n'
        f'generator-settings={FLAT}\n')

    log_path = work / 'server.log'
    out = log_path.open('w')
    proc = subprocess.Popen(['java', f'-Xmx{args.xmx}', FORGE_ARGS, 'nogui'], cwd=work,
                            stdin=subprocess.PIPE, stdout=out, stderr=out, text=True)

    def log():
        return log_path.read_text(errors='replace')

    def wait_for(text, timeout):
        end = time.monotonic() + timeout
        while time.monotonic() < end:
            if text in log():
                return True
            if proc.poll() is not None:
                return False
            time.sleep(0.25)
        return False

    def send(command):
        proc.stdin.write(command + '\n')
        proc.stdin.flush()

    ok = True
    try:
        if not wait_for('Done (', 240):
            print(f'FAIL server did not start: {log_path}')
            ok = False
            return 1
        print('PASS server started', flush=True)
        for index, command in enumerate(args.command):
            send(command)
            pause = args.wait_ticks[index] if index < len(args.wait_ticks) else 20
            time.sleep(max(1, pause) / 20.0)
        send('stop')
        code = proc.wait(timeout=120)
        out.close()
        text = log()
        if code != 0:
            print(f'FAIL server exited {code}')
            ok = False
        else:
            print('PASS server stopped cleanly')
        suspicious = [line for line in text.splitlines()
                      if 'mcaconversations' in line.lower() and ('Exception' in line or '/ERROR]' in line)]
        if suspicious:
            print('FAIL lines naming mcaconversations at ERROR or with an exception:')
            for line in suspicious[:20]:
                print('  ' + line)
            ok = False
        else:
            print('PASS no ERROR or exception line names mcaconversations')
    finally:
        if proc.poll() is None:
            try:
                send('stop')
                proc.wait(timeout=30)
            except Exception:
                proc.terminate()
        print(f'Artifacts: {work}')
        print(f'Log: {log_path}')
    return 0 if ok else 1


if __name__ == '__main__':
    sys.exit(main())

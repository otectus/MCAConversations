#!/usr/bin/env python3
"""Verify shared release content and the reviewed Minecraft/loader adaptations.

Usage: python3 tools/verify_release_parity.py --forge PATH --neoforge PATH
           [--manifest tools/parity/parity-<release>-adaptations.json]
Without --manifest the newest tools/parity/parity-*-adaptations.json in this repository is used, "newest"
meaning the highest release version parsed from the filename, not the highest string. A relative
--manifest is resolved against this repository, not the caller's working directory.
Every shared file is compared. Platform differences are pinned to reviewed hashes;
new files or changed adapters fail until the corresponding difference is reviewed.
The expected release and protocol are read from the manifest, which records the reviewed
release; the version strings themselves live only in gradle.properties.
"""
import argparse
from collections import Counter
import hashlib
import json
from pathlib import Path
import re
import sys

SCOPES = ('src/main/java', 'src/test/java', 'src/main/resources', 'src/content')


def content(path):
    if path is None:
        return None
    data = path.read_bytes()
    try:
        return data.decode('utf-8').replace('\r\n', '\n').replace('\r', '\n').encode('utf-8')
    except UnicodeDecodeError:
        return data


def digest(data):
    return hashlib.sha256(data).hexdigest() if data is not None else None


def inventory(root):
    files = {}
    for scope in SCOPES:
        for path in (root / scope).rglob('*'):
            if path.is_file():
                files[path.relative_to(root).as_posix()] = path
    # 1.21.1 examples split server data and client languages into separate packs.
    for path in (root / 'datapack_samples').rglob('*'):
        if path.is_file() and path.name not in ('README.md', 'pack.mcmeta'):
            key = re.sub(r'/(datapack|resourcepack)/(?=data/|assets/)', '/', path.relative_to(root).as_posix())
            if key in files:
                raise ValueError(f'Duplicate parity path: {key}')
            files[key] = path
    return files


def properties(root):
    return dict(re.findall(r'^\s*([\w.]+)\s*=\s*([^\r\n]*)',
                           (root / 'gradle.properties').read_text(), re.MULTILINE))


def compare(forge, neoforge, reviewed, release, protocol):
    left, right = inventory(forge), inventory(neoforge)
    counts = Counter()
    errors = []
    used = set()
    for key in sorted(left.keys() | right.keys()):
        a, b = content(left.get(key)), content(right.get(key))
        if a == b:
            counts['identical'] += 1
            continue
        hashes = {'forge': digest(a), 'neoforge': digest(b)}
        expected = reviewed.get(key)
        if expected and hashes == {k: expected.get(k) for k in hashes}:
            counts['reviewed_adaptations'] += 1
            used.add(key)
        else:
            errors.append(f'Unreviewed difference: {key}')
    for key in reviewed.keys() - used:
        errors.append(f'Stale or changed reviewed adaptation: {key}')
    fp, np = properties(forge), properties(neoforge)
    for key in ('mod_version', 'network_protocol', 'mod_id'):
        if fp.get(key) != np.get(key):
            errors.append(f'Metadata differs: {key}')
    if fp.get('mod_version') != release or fp.get('network_protocol') != protocol:
        errors.append(f'Expected release {release} and protocol {protocol}')
    return counts, errors


ROOT = Path(__file__).resolve().parents[1]


def release_key(path):
    """Sort key for tools/parity/parity-<release>-adaptations.json, by version rather than by string,
    so 1.10.0 sorts after 1.9.0 and a non-numeric part sorts before any number."""
    version = path.name[len('parity-'):-len('-adaptations.json')]
    return tuple((0, int(part)) if part.isdigit() else (-1, 0)
                 for part in re.split(r'[.\-+]', version))


def newest_manifest():
    manifests = sorted((ROOT / 'tools' / 'parity').glob('parity-*-adaptations.json'), key=release_key)
    if not manifests:
        raise SystemExit(f'No parity manifest found under {ROOT / "tools" / "parity"}')
    return manifests[-1]


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--forge', type=Path, required=True)
    parser.add_argument('--neoforge', type=Path, required=True)
    parser.add_argument('--manifest', type=Path, default=None,
                        help='adaptation manifest; a relative path is resolved against this '
                             'repository. Defaults to the newest tools/parity/parity-*-adaptations.json.')
    args = parser.parse_args()
    path = newest_manifest() if args.manifest is None else ROOT / args.manifest
    manifest = json.loads(path.read_text())
    counts, errors = compare(args.forge.resolve(), args.neoforge.resolve(), manifest['files'],
                             manifest['release'], manifest['protocol'])
    print(json.dumps(dict(counts), sort_keys=True))
    for error in errors:
        print(error, file=sys.stderr)
    if errors:
        return 1
    print('Release parity verified: shared files match and every platform difference is accounted for.')
    return 0


if __name__ == '__main__':
    sys.exit(main())

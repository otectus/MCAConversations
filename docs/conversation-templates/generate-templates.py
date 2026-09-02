# -*- coding: utf-8 -*-
"""Generate fill-in authoring templates for every conversation MCA: Conversations ships."""
import json, os, glob, re, collections

R    = 'src/main/resources'
DATA = R + '/data/mcaconversations'
OUT  = 'docs/conversation-templates'
BLANK = '.' * 44

def load(p):
    with open(p, encoding='utf-8') as f:
        return json.load(f)

# ---------------------------------------------------------------- lang corpus
base_en = load(R + '/assets/mca_dialogue/lang/en_us.json')
base_pt = load(R + '/assets/mca_dialogue/lang/pt_br.json')
PERS = sorted(os.path.basename(p)[len('mca_dialogue_'):]
              for p in glob.glob(R + '/assets/mca_dialogue_*'))
ov_en, ov_pt = {}, {}
for p in PERS:
    ov_en[p] = load(R + '/assets/mca_dialogue_%s/lang/en_us.json' % p)
    ov_pt[p] = load(R + '/assets/mca_dialogue_%s/lang/pt_br.json' % p)

def stem_index(langmap, strip=''):
    """stem -> ordered list of suffixes ('' for the bare key, '/3' for a variant)."""
    idx = collections.defaultdict(list)
    for k in langmap:
        if strip:
            if not k.startswith(strip):
                continue
            key = k[len(strip):]
        else:
            key = k
        if '/' in key:
            s, n = key.rsplit('/', 1)
            if n.isdigit():
                idx[s].append(int(n))
                continue
        idx[key].append(0)
    return {s: ['' if n == 0 else '/%d' % n for n in sorted(ns)] for s, ns in idx.items()}

BASE_IDX = stem_index(base_en)
OV_IDX   = {p: stem_index(ov_en[p], strip=p + '.') for p in PERS}

def variants(stem):
    return [(suf, base_en.get(stem + suf), base_pt.get(stem + suf))
            for suf in BASE_IDX.get(stem, [])]

def overlays(stem):
    out = {}
    for p in PERS:
        sufs = OV_IDX[p].get(stem)
        if not sufs:
            continue
        out[p] = [(suf, ov_en[p].get(p + '.' + stem + suf), ov_pt[p].get(p + '.' + stem + suf))
                  for suf in sufs]
    return out

# ---------------------------------------------------------------- dialogue graph
questions = {}
for f in sorted(glob.glob(DATA + '/dialogues/*.json')):
    questions[os.path.basename(f)[:-5]] = load(f)

# ---------------------------------------------------------------- contracts
beats, replies = {}, {}
for f in sorted(glob.glob(DATA + '/conversation_beats/*.json')):
    j = load(f)
    beats.update(j.get('beats', {}))
    replies.update(j.get('replies', {}))
beat_by_say = collections.defaultdict(list)
for bid, b in beats.items():
    beat_by_say[b.get('say')].append((bid, b))

intents = {}
for f in sorted(glob.glob(DATA + '/chat_intents/*.json')):
    intents.update(load(f).get('intents', {}))
intent_by_button = collections.defaultdict(list)
for iid, it in intents.items():
    intent_by_button[(it.get('question'), it.get('answer'))].append((iid, it))

catalog = {}
for f in sorted(glob.glob(DATA + '/conversation_catalog/*.json')):
    catalog.update(load(f).get('topics', {}))

profiles = {}
for f in sorted(glob.glob(DATA + '/profession_profiles/*.json')):
    profiles.update(load(f).get('profiles', {}))
PROF_IDS = sorted({k.split(':', 1)[1] for k in profiles})

# ---------------------------------------------------------------- glossing
def rng(v):
    lo, hi = v.get('min'), v.get('max')
    if lo is not None and hi is not None: return '%s..%s' % (lo, hi)
    if lo is not None: return '>= %s' % lo
    if hi is not None: return '<= %s' % hi
    return 'any value'

def gloss(k, v):
    if k == 'conversations_disposition':
        return 'disposition %s %s' % (v.get('axis'), rng(v))
    if k == 'conversations_enabled':  return 'the `%s` feature is ON' % v
    if k == 'conversations_disabled': return 'the `%s` feature is OFF' % v
    if k == 'conversations_check':
        return ('the dialogue check `%s` lands on tier **%s** (axis %s, difficulty %s%s%s)'
                % (v.get('id'), v.get('tier'), v.get('axis'), v.get('difficulty'),
                   ', stance ' + v['stance'] if v.get('stance') else '',
                   ', arc ' + v['arc'] if v.get('arc') else ''))
    if k == 'conversations_progress':
        if 'arc' in v:       return 'arc `%s` is at stage %s' % (v['arc'], rng(v))
        if 'milestone' in v: return 'milestone `%s` %s' % (v['milestone'], 'is set' if v.get('has', True) else 'is NOT set')
        if 'exclusive' in v: return 'exclusive `%s` is `%s`' % (v['exclusive'], v.get('is'))
    if k == 'conversations_session':
        bits = [x for x in ('topic', 'branch') if v.get(x)]
        return 'the live session is in ' + ' / '.join('%s `%s`' % (b, v[b]) for b in bits)
    if k == 'conversations_budget':
        return ("today's affection ledger, axis %s %s%s" % (v.get('axis'), rng(v),
                ' for decision `%s`' % v['decision'] if v.get('decision') else ''))
    if k == 'conversations_relationship':
        return 'the relationship band is one of %s' % ', '.join('`%s`' % b for b in v.get('bands', []))
    if k == 'conversations_personality':
        return 'the personality is %s' % (', '.join('`%s`' % x for x in v) if isinstance(v, list) else '`%s`' % v)
    if k == 'conversations_weather':  return 'the sky is `%s`' % v.get('is')
    if k == 'conversations_season':   return 'the season is `%s`' % v.get('is')
    if k == 'conversations_holiday':  return 'the festival is `%s`' % v.get('is')
    if k == 'conversations_gossip':
        t = v.get('types') if isinstance(v, dict) else None
        return 'an untold village event exists%s' % (' of type ' + '/'.join(t) if t else '')
    if k == 'conversations_quest':
        return 'quest state %s' % json.dumps(v, ensure_ascii=False)
    if k == 'memory':
        neg = isinstance(v, dict) and v.get('dividend', 1) < 0
        scope = ' (this player only)' if isinstance(v, dict) and v.get('var') == 'player' else ' (villager-wide)'
        mid = v.get('id') if isinstance(v, dict) else v
        return '%s the memory `%s`%s' % ('LACKS' if neg else 'has', mid, scope)
    if k == 'age_group':   return 'the villager is a `%s`' % v
    if k == 'hearts_min':  return 'hearts >= %s' % v
    if k == 'hearts_max':  return 'hearts <= %s' % v
    if k == 'rank':        return 'the village rank is `%s`' % v
    if k == 'mood':        return 'the mood is `%s`' % v
    if k == 'is_pregnant': return 'the villager is pregnant' if v else 'the villager is not pregnant'
    return '`%s` = %s' % (k, json.dumps(v, ensure_ascii=False))

def cond_lines(conds):
    if not conds:
        return ['always eligible (no conditions on it)']
    out = []
    for c in conds:
        ch = c.get('chance', 0)
        body = ' AND '.join(gloss(k, v) for k, v in c.items() if k != 'chance')
        if ch is not None and ch <= -500:
            out.append('RULED OUT when %s  _(chance %s)_' % (body, ch))
        elif ch is not None and ch < 0:
            out.append('made less likely when %s  _(chance %s)_' % (body, ch))
        else:
            out.append('weighted +%s when %s' % (ch, body))
    return out

def act_lines(a):
    out = []
    aff = a.get('conversations_affection_apply')
    if isinstance(aff, dict):
        out.append('**hearts %+d** — decision id `%s`, budget `%s`, replay policy `%s`'
                   % (aff.get('delta', 0), aff.get('decision'), aff.get('budget', '(the session default)'),
                      aff.get('policy', 'daily_repeat')))
    for raw in ('positive', 'negative'):
        if raw in a:
            out.append('**hearts (raw MCA `%s` field)** = %s' % (raw, a[raw]))
    dis = a.get('conversations_disposition_apply')
    if isinstance(dis, dict):
        out.append('disposition — %s  _(recorded under topic `%s`)_'
                   % (', '.join('%s %+d' % (k, v) for k, v in dis.get('deltas', {}).items()), dis.get('topic')))
    prg = a.get('conversations_progress_apply')
    if prg is not None:
        for e in (prg if isinstance(prg, list) else [prg]):
            if 'arc' in e:
                out.append('arc `%s` — %s%s' % (e['arc'], e.get('op', 'advance'),
                                                ' to stage %s' % e['to'] if 'to' in e else ''))
            elif 'milestone' in e:
                out.append('milestone `%s` set (fires once, ever)' % e['milestone'])
            elif 'exclusive' in e:
                out.append('exclusive `%s` -> `%s` (locks the other side out for good)'
                           % (e['exclusive'], e.get('member')))
    ses = a.get('conversations_session')
    if isinstance(ses, dict):
        out.append('session `%s`%s%s%s' % (ses.get('op'),
                   ' topic `%s`' % ses['topic'] if ses.get('topic') else '',
                   ' branch `%s`' % ses['branch'] if ses.get('branch') else '',
                   ' budget `%s`' % ses['budget'] if ses.get('budget') else ''))
    rem = a.get('remember')
    if isinstance(rem, dict):
        out.append('remembers `%s`%s%s' % (rem.get('id'),
                   ' (this player only)' if rem.get('var') == 'player' else '',
                   ' for %s ticks' % rem['time'] if rem.get('time') else ' permanently'))
    rec = a.get('conversations_record')
    if rec is not None:
        for e in (rec if isinstance(rec, list) else [rec]):
            out.append('remembers `%s`%s%s' % (e.get('id'),
                       ' (this player only)' if e.get('var') == 'player' else '',
                       ' for %s ticks' % e['time'] if e.get('time') else ' permanently'))
    gos = a.get('conversations_gossip_say')
    if gos is not None:
        pre = gos.get('phrase_prefix', 'conversations.gossip') if isinstance(gos, dict) else 'conversations.gossip'
        out.append('tells the next untold village event, from the pool `dialogue.%s.<event type>`' % pre)
    known = {'conversations_affection_apply', 'conversations_disposition_apply',
             'conversations_progress_apply', 'conversations_session', 'remember',
             'conversations_record', 'conversations_gossip_say', 'next', 'say',
             'conversations_say', 'positive', 'negative'}
    for k in a:
        if k not in known:
            out.append('`%s` = %s' % (k, json.dumps(a[k], ensure_ascii=False)))
    return out or ['nothing — this reply neither costs nor pays anything']

def say_of(a):
    if 'say' in a:
        return a['say'], []
    cs = a.get('conversations_say')
    if isinstance(cs, dict) and cs.get('phrase'):
        return cs['phrase'], cs.get('vars', []) or []
    return None, []

# ---------------------------------------------------------------- usage maps
usage    = collections.defaultdict(list)
inbound  = collections.defaultdict(list)
say_vars = collections.defaultdict(set)
for qn, q in questions.items():
    for ans in q.get('answers', []):
        an = ans.get('name', '(auto)')
        for res in ans.get('results', []):
            a = res.get('actions', {}) or {}
            sk, vs = say_of(a)
            nxt = a.get('next')
            if sk:
                usage[sk].append((qn, an, nxt))
                for v in vs:
                    say_vars[sk].add(v)
            if nxt:
                inbound[nxt].append((sk, qn, an))

# ---------------------------------------------------------------- grouping
def group_of(name):
    p = name.split('.')
    if p[0] != 'conversations':
        return ('00-hub', 'Hub, greeting and category pages')
    if len(p) == 1 or p[1] == 'cat':
        return ('00-hub', 'Hub, greeting and category pages')
    if p[1] in ('topic', 'arc', 'scene') and len(p) > 2:
        head = p[2]
        if head == 'work' and len(p) > 3 and p[3] in PROF_IDS:
            return ('work-' + p[3], 'Work talk with a %s' % p[3].replace('_', ' '))
        return ('topic-' + head, 'Topic: %s' % head)
    return ('topic-' + p[1], 'Topic: %s' % p[1])

groups = collections.defaultdict(list)
titles = {}
for qn in questions:
    gid, title = group_of(qn)
    groups[gid].append(qn)
    titles[gid] = title

# ---------------------------------------------------------------- rendering
def esc(s):
    if not isinstance(s, str):
        return '(none)'
    return s.replace('\r', '').replace('\n', ' ')

def anchor(name):
    return re.sub(r'[^a-z0-9]+', '-', name.lower()).strip('-')

def pool_block(out, stem, who, context_lines, notes, first_seen, args):
    vs = variants(stem)
    out.append('')
    out.append('```text')
    out.append('POOL   dialogue key: %s' % stem)
    out.append('WHO    %s' % who)
    for c in context_lines:
        out.append('       %s' % c)
    out.append('ARGS   %s' % args)
    out.append('SIZE   %d line%s in this pool%s'
               % (len(vs), '' if len(vs) == 1 else 's',
                  ' — the game picks one at random each time, so they must be interchangeable'
                  if len(vs) > 1 else ''))
    for n in notes:
        out.append('NOTE   %s' % n)
    out.append('```')
    seen = first_seen.get(stem)
    if seen and seen is not True:
        out.append('')
        out.append('> Written out in full under **%s** earlier in this file. Fill it in there, once.' % seen)
        return
    if not vs:
        out.append('')
        out.append('> **No English line ships under this key.** Write one, and its `pt_br` twin.')
        out.append('')
        out.append('```text')
        out.append('  %s' % stem)
        out.append('    en  (missing)')
        out.append('    >>  ' + BLANK)
        out.append('    pt  (missing)')
        out.append('    >>  ' + BLANK)
        out.append('```')
        return
    out.append('')
    out.append('```text')
    for suf, en, pt in vs:
        out.append('  %s%s   [%d chars]' % (stem, suf, len(en or '')))
        out.append('    en  %s' % esc(en))
        out.append('    >>  ' + BLANK)
        out.append('    pt  %s' % (esc(pt) if pt is not None else '(MISSING - locale parity will fail)'))
        out.append('    >>  ' + BLANK)
    out.append('```')
    ovs = overlays(stem)
    if ovs:
        out.append('')
        out.append('<details><summary><b>Per-personality versions &mdash; %d of %d personalities override '
                   'this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>'
                   % (len(ovs), len(PERS)))
        out.append('')
        out.append('```text')
        for p in sorted(ovs):
            for suf, en, pt in ovs[p]:
                out.append('  %s.%s%s' % (p, stem, suf))
                out.append('    en  %s' % esc(en))
                out.append('    >>  ' + BLANK)
                out.append('    pt  %s' % esc(pt))
                out.append('    >>  ' + BLANK)
        out.append('```')
        out.append('')
        out.append('</details>')
        missing = [p for p in PERS if p not in ovs]
        if missing:
            out.append('')
            out.append('> Falls back to the base pool above, no voice of its own here: %s.' % ', '.join(missing))

def offers(qn):
    q = questions.get(qn)
    if not q:
        return []
    out = []
    for ans in q.get('answers', []):
        an = ans.get('name', '(auto)')
        vs = variants('dialogue.%s.%s' % (qn, an))
        lab = vs[0][1] if vs else '(auto — no button)'
        out.append('"%s"' % esc(lab))
    return out

def render_answer(out, qn, ans, first_seen, heading='###', cut=None):
    an = ans.get('name', '(auto)')
    lab_stem = 'dialogue.%s.%s' % (qn, an)
    lab_vs = variants(lab_stem)
    label = lab_vs[0][1] if lab_vs else '(no label — this is an auto answer)'
    out.append('')
    out.append('%s Button `%s` — "%s"' % (heading, an, esc(label)))
    rc = replies.get('%s/%s' % (qn, an))
    meta = []
    if rc:
        meta.append('stance family `%s`' % rc.get('stance'))
        if rc.get('tone'):        meta.append('tone `%s`' % rc.get('tone'))
        if rc.get('outcomes'):    meta.append('outcome %s' % '/'.join('`%s`' % o for o in rc['outcomes']))
        if rc.get('responds_to'): meta.append('answers the beat(s) %s' % ', '.join('`%s`' % b for b in rc['responds_to']))
        if rc.get('requires_facts'):
            meta.append('offered only once the villager has actually said %s'
                        % ', '.join('`%s`' % b for b in rc['requires_facts']))
        if rc.get('exit'):        meta.append('**this is the graceful way out of the node**')
    if meta:
        out.append('')
        out.append('*%s*' % ' · '.join(meta))
    if ans.get('constraints'):
        out.append('')
        out.append('Shown only when MCA\'s own constraints hold: `%s`'
                   % json.dumps(ans['constraints'], ensure_ascii=False))
    ints = intent_by_button.get((qn, an), [])
    if ints:
        out.append('')
        out.append('In chat mode the player can type this instead of clicking. Rewrite the button and '
                   'these phrasings together, or typing it stops working:')
        for iid, it in ints:
            out.append('- intent `%s` — accepted phrasings: %s'
                       % (iid, '; '.join('"%s"' % p for p in it.get('phrases', []))))
            if it.get('requiresAny'):
                out.append('  - the message must contain one of: %s'
                           % ', '.join('`%s`' % w for w in it['requiresAny']))
            if it.get('keywords'):
                out.append('  - scored words: %s'
                           % ', '.join('`%s`(%s)' % (w, s) for w, s in it['keywords'].items()))
    pool_block(out, lab_stem,
               'PLAYER — the words printed on the button the player presses',
               ['on the node: %s' % qn],
               ['Write it as something a person would actually say, in the player\'s voice. Never name '
                'the mechanic ("Persuade", "Gain trust") and never show a number.',
                'It must make sense as a reply to every line that can open this node (listed above).'],
               first_seen,
               'none — button labels take no substitutions; write plain text')
    first_seen[lab_stem] = True
    out.append('')
    out.append('**What pressing it does, and what the villager says back:**')
    n_res = len(ans.get('results', []))
    for i, res in enumerate(ans.get('results', []), 1):
        a = res.get('actions', {}) or {}
        sk, vs = say_of(a)
        nxt = a.get('next')
        if cut:
            cut()
        out.append('')
        out.append('**Outcome %d of %d** — base weight `%s`%s'
                   % (i, n_res, res.get('baseChance', 0),
                      '  ·  _last in the list, so this is the safety net MCA falls back to when nothing '
                      'else scores_' if i == n_res and n_res > 1 else ''))
        out.append('')
        for cl in cond_lines(res.get('conditions')):
            out.append('- Fires when: %s' % cl)
        for al in act_lines(a):
            out.append('- Does: %s' % al)
        if nxt:
            out.append('- Then opens: `%s`' % nxt)
            ofs = offers(nxt)
            if ofs:
                out.append('- …where the player\'s next choices will be: %s' % ' | '.join(ofs))
        else:
            out.append('- Then: the exchange stops here')
        if not sk:
            out.append('- The villager says **no line of its own** here — the destination node\'s own '
                       'prompt is what the player reads.')
            continue
        stem = 'dialogue.' + sk
        notes = []
        for bid, b in beat_by_say.get(sk, []):
            if nxt and b.get('response_question') not in (None, nxt):
                continue
            notes.append('beat `%s`: the villager %ss. Subject `%s`, polarity `%s`, %s, outcome `%s`.'
                         % (bid, b.get('npc_act'), b.get('subject'), b.get('polarity'),
                            (b.get('openness') or '').replace('_', ' '), b.get('outcome')))
            if b.get('facts'):
                notes.append('this is the line that establishes %s — later lines read it back, so the '
                             'replacement must still say it' % ', '.join('`%s`' % x for x in b['facts']))
            st = b.get('allowed_stances') or []
            if st and len(st) <= 7:
                notes.append('the buttons that answer it may only take these stances, so leave room for '
                             'exactly them: %s' % ', '.join(st))
            elif st:
                notes.append('the buttons that answer it may take almost any stance (%d families), so it '
                             'must not close the subject down' % len(st))
            if (b.get('context') or {}).get('ages'):
                notes.append('only ever spoken by a %s' % '/'.join(b['context']['ages']))
        others = [u for u in usage.get(sk, []) if not (u[0] == qn and u[1] == an)]
        if others:
            notes.append('the same pool is also spoken at: %s%s'
                         % ('; '.join('%s / %s' % (q2, a2) for q2, a2, _ in others[:8]),
                            ' …and %d more' % (len(others) - 8) if len(others) > 8 else ''))
        args = "%1$s = the player's name (MCA prepends it to every line; using it is optional)"
        extra = sorted(say_vars.get(sk, set()))
        if extra:
            args += ' · ' + ' · '.join('%%%d$s = %s' % (n + 2, v) for n, v in enumerate(extra))
        pool_block(out, stem,
                   'VILLAGER — what the player reads after pressing "%s"' % esc(label),
                   ['spoken on: %s, button `%s`' % (qn, an),
                    'leaves the player on: %s' % (nxt or '(the exchange stops)')],
                   notes, first_seen, args)
        first_seen.setdefault(stem, '`%s` / button `%s`' % (qn, an))

def render_node_chunks(qn, first_seen):
    """[(node, is_header, lines)] so an oversized node can straddle several part files."""
    chunks = []
    cur = []

    def cut():
        if cur:
            chunks.append((qn, not chunks, list(cur)))
            del cur[:]

    render_node(cur, qn, first_seen, cut=cut)
    if cur:
        chunks.append((qn, not chunks, list(cur)))
    return chunks


def render_node(out, qn, first_seen, cut=None):
    q = questions[qn]
    out.append('')
    out.append('## `%s`' % qn)
    out.append('')
    flags = [f for f in ('auto', 'silent') if q.get(f)]
    if flags:
        note = ' An `auto` node shows no buttons — it plays its one answer straight through.' if 'auto' in flags else ''
        out.append('Question flags: %s.%s' % (', '.join('`%s`' % f for f in flags), note))
        out.append('')
    ins = inbound.get(qn, [])
    if ins:
        shown = '; '.join('`%s` / `%s`' % (f, a) for _, f, a in ins[:12])
        more = ' …and %d more' % (len(ins) - 12) if len(ins) > 12 else ''
        out.append('**Reached from %d route(s):** %s%s' % (len(ins), shown, more))
        out.append('')
        pools = sorted({s for s, _, _ in ins if s})
        if pools:
            out.append('The lines that can open it (write every button below so it answers *all* of them):')
            for s in pools[:14]:
                vs = variants('dialogue.' + s)
                sample = esc(vs[0][1]) if vs else '(no line)'
                out.append('- `%s` — e.g. "%s"' % (s, sample))
            if len(pools) > 14:
                out.append('- …and %d more pools' % (len(pools) - 14))
            out.append('')
    stem = 'dialogue.' + qn
    pool_block(out, stem,
               "VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they "
               "arrive here on a result that carried no line of its own.",
               ['node: %s' % qn],
               ['Write it so it reads correctly cold, with nothing before it.'],
               first_seen, "%1$s = the player's name")
    first_seen.setdefault(stem, '`%s` (the node prompt)' % qn)
    for ans in q.get('answers', []):
        if cut:
            cut()
        render_answer(out, qn, ans, first_seen, cut=cut)
    out.append('')
    out.append('---')

def render_group(gid):
    names = sorted(groups[gid])
    out = []
    topic = gid[len('topic-'):] if gid.startswith('topic-') else None
    out.append('# %s' % titles[gid])
    out.append('')
    out.append('> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.')
    out.append('> Read [README.md](README.md) once first — it carries the rules the build enforces.')
    out.append('')
    if topic and topic in catalog:
        c = catalog[topic]
        out.append('## What this conversation is')
        out.append('')
        out.append('| | |')
        out.append('|---|---|')
        out.append('| Topic id | `%s` |' % topic)
        out.append('| Opened from | question `%s`, button `%s` |'
                   % (c.get('entry', {}).get('question'), c.get('entry', {}).get('answer')))
        out.append('| Depth class (its heart budget) | `%s` |' % c.get('depth'))
        out.append('| Returns to | `%s` |' % c.get('return_question'))
        out.append('| Ages that can reach it | %s |' % ', '.join(c.get('ages', [])))
        out.append('| Stance families it must offer | %s |'
                   % ', '.join('`%s`' % s for s in c.get('required_stance_families', [])))
        if c.get('arc'):
            out.append('| Narrative arc | `%s`, max stage %s |' % (c['arc'].get('id'), c['arc'].get('max_stage')))
        if c.get('milestones'):
            out.append('| Milestones it can set | %s |' % ', '.join('`%s`' % m for m in c['milestones']))
        if c.get('exclusives'):
            out.append('| Mutually exclusive choices | `%s` |'
                       % json.dumps(c['exclusives'], ensure_ascii=False))
        out.append('| Typable in chat mode | %s |' % ('yes' if c.get('chat_required') else 'no'))
        out.append('')
        ent = c.get('entry', {})
        eq, ea = ent.get('question'), ent.get('answer')
        if eq in questions:
            match = [a for a in questions[eq].get('answers', []) if a.get('name') == ea]
            if match:
                out.append('### The way in')
                out.append('')
                out.append('The button that opens this whole conversation sits on `%s`, which is written '
                           'out in **%s.md**. Rewrite the outcomes behind it there, once. The button\'s own '
                           'wording is repeated here because it is the first thing a player reads about '
                           'this subject.' % (eq, group_of(eq)[0]))
                lab_stem = 'dialogue.%s.%s' % (eq, ea)
                pool_block(out, lab_stem,
                           'PLAYER — the button that opens this whole conversation',
                           ['on the node: %s' % eq],
                           ['If you change it, change the same key in %s*.md too — it is one key, '
                            'shown in two places.' % group_of(eq)[0]],
                           {}, 'none — button labels take no substitutions')
                out.append('')
                out.append('---')
    header = '\n'.join(out) + '\n'
    first_seen = {}
    chunks = []
    for n in names:
        chunks.extend(render_node_chunks(n, first_seen))
    return header, chunks

# ---------------------------------------------------------------- write
PART_BUDGET = 600000
os.makedirs(OUT, exist_ok=True)
written = []          # (filename, title, nodes, bytes)
group_files = {}      # gid -> [filenames]

for gid in sorted(groups):
    header, chunks = render_group(gid)
    parts, cur, cur_len = [], [], 0
    for name, is_header, lines in chunks:
        body = '\n'.join(lines) + '\n'
        if cur and cur_len + len(body) > PART_BUDGET:
            parts.append(cur)
            cur, cur_len = [], 0
        cur.append((name, is_header, body))
        cur_len += len(body)
    if cur:
        parts.append(cur)
    files = []
    for i, part in enumerate(parts, 1):
        fn = gid + '.md' if len(parts) == 1 else '%s-part%d.md' % (gid, i)
        files.append(fn)
    for i, part in enumerate(parts, 1):
        body = []
        if i == 1:
            body.append(header)
        else:
            body.append('# %s — part %d of %d\n' % (titles[gid], i, len(parts)))
            body.append('> Continued from [%s](%s). Read [README.md](README.md) first.\n'
                        % (files[0], files[0]))
        if len(parts) > 1:
            body.append('**Parts of this conversation:** '
                        + ' · '.join('[part %d](%s)' % (j + 1, f) for j, f in enumerate(files)) + '\n')
        body.append('\n## Nodes in this file\n')
        seen_nodes = []
        for name, _is_h, _b in part:
            if name not in seen_nodes:
                seen_nodes.append(name)
        for name in seen_nodes:
            body.append('- [`%s`](#%s)' % (name, anchor(name)))
        body.append('\n---')
        first = True
        for name, is_header, chunk in part:
            if first and not is_header:
                body.append('\n## `%s` — continued\n' % name)
            first = False
            body.append(chunk)
        text = '\n'.join(body) + '\n'
        with open(os.path.join(OUT, files[i - 1]), 'w', encoding='utf-8', newline='\n') as f:
            f.write(text)
        written.append((files[i - 1], titles[gid], len(seen_nodes), len(text)))
    group_files[gid] = files

# ------------------------------------------------- coverage: what has been shown
shown = set()
for gid in groups:
    for qn in groups[gid]:
        shown.add('dialogue.' + qn)
        for ans in questions[qn].get('answers', []):
            shown.add('dialogue.%s.%s' % (qn, ans.get('name', '(auto)')))
            for res in ans.get('results', []):
                sk, _ = say_of(res.get('actions', {}) or {})
                if sk:
                    shown.add('dialogue.' + sk)

PREFIX_NOTES = [
    ('dialogue.chatmode.', 'Chat-mode plumbing: what a villager says when you speak to them in the chat '
                           'box rather than through the menu — attention, being busy, asking you to '
                           'clarify, not understanding. Spoken constantly, so these carry a lot of voice.'),
    ('dialogue.conversations.gossip', 'Village gossip. %2$s is the first subject\'s name, %3$s the '
                                      'second (empty for deaths, births, arrivals and departures).'),
    ('dialogue.conversations.fallback', 'Said when a topic is switched off in the config, or nothing '
                                        'else fits. It must never sound like an error.'),
    ('dialogue.greet', 'The line a villager opens with when you walk up to them.'),
    ('dialogue.chat', 'The header MCA shows when the Chat button drops you into the hub.'),
    ('dialogue.mcareputation', 'Lines tied to MCA: Reputation.'),
    ('dialogue.story', 'Story lines.'),
    ('dialogue.shake_hand', 'The handshake interaction.'),
    ('dialogue.main', 'MCA\'s own root menu, extended by this mod.'),
    ('dialogue.conversations', 'Conversation lines this mod ships that no shipped dialogue result '
                               'points at right now — spare pools, or lines reached through code '
                               'rather than through a result.'),
]

leftover = sorted(s for s in BASE_IDX if s not in shown)
if leftover:
    out = ['# Loose lines — everything else in `mca_dialogue`', '',
           '> Every remaining pool in `assets/mca_dialogue/lang/` that no dialogue result names '
           'directly. Nothing in the corpus is left out of these templates; this is where the rest '
           'of it lives.', '',
           '> Read [README.md](README.md) first.', '']
    buckets = collections.OrderedDict()
    for pre, note in PREFIX_NOTES:
        buckets[pre] = (note, [])
    buckets['(other)'] = ('Anything that matched none of the groups above.', [])
    for stem in leftover:
        for pre, _ in PREFIX_NOTES:
            if stem.startswith(pre):
                buckets[pre][1].append(stem)
                break
        else:
            buckets['(other)'][1].append(stem)
    for pre, (note, stems) in buckets.items():
        if not stems:
            continue
        out.append('## `%s`' % pre)
        out.append('')
        out.append(note)
        out.append('')
        fs = {}
        for stem in stems:
            pool_block(out, stem, 'see the group note above',
                       ['no shipped dialogue result names this pool directly'],
                       [], fs, "%1$s = the player's name; check the current text for any %2$s+ it uses")
        out.append('')
        out.append('---')
    with open(os.path.join(OUT, '98-loose-lines.md'), 'w', encoding='utf-8', newline='\n') as f:
        txt = '\n'.join(out) + '\n'
        f.write(txt)
    written.append(('98-loose-lines.md', 'Loose lines — everything else in mca_dialogue',
                    len(leftover), len(txt)))

# ------------------------------------------------- the mcaconversations namespace
mc_en = load(R + '/assets/mcaconversations/lang/en_us.json')
mc_pt = load(R + '/assets/mcaconversations/lang/pt_br.json')
MC_NOTES = [
    ('mcaconversations.slot.', 'Noun phrases the scene generator drops into a generated line as '
                              '%2$s, %3$s… — "a rotten post", "a gate that sticks". They must read '
                              'naturally mid-sentence, lower case, no full stop.'),
    ('mcaconversations.culture.', 'A village\'s cultural character, named inside lines about the village.'),
    ('mcaconversations.hub.', 'Wording on the conversation hub itself.'),
    ('mcaconversations.fallback.', 'What a villager says when a feature is off or nothing fits.'),
    ('mcaconversations.season.', 'Fills the `season` template var: "the height of summer".'),
    ('mcaconversations.weather.', 'Fills the `weather` template var: "the rain".'),
    ('mcaconversations.time_of_day.', 'Fills the `time_of_day` template var: "this evening".'),
    ('mcaconversations.holiday.', 'Fills the `holiday` template var: "the harvest festival".'),
    ('gui.mcaconversations.', 'Interface text on the numbered-response UI.'),
    ('chat.mcaconversations.', 'Interface text shown in the chat box.'),
    ('analysis.', 'Names for this mod\'s conditions in MCA\'s debug/analysis screen.'),
]
out = ['# The mod\'s own words — slots, world words and interface text', '',
       '> These live in `assets/mcaconversations/lang/`, not in `mca_dialogue`. They have no '
       'personality overlays: one wording serves every villager.', '',
       '> Read [README.md](README.md) first.', '']
mc_idx = stem_index(mc_en)
placed = set()
for pre, note in MC_NOTES:
    stems = sorted(s for s in mc_idx if s.startswith(pre))
    if not stems:
        continue
    placed.update(stems)
    out.append('## `%s`' % pre)
    out.append('')
    out.append(note)
    out.append('')
    out.append('```text')
    for s in stems:
        for suf in mc_idx[s]:
            k = s + suf
            out.append('  %s' % k)
            out.append('    en  %s' % esc(mc_en.get(k)))
            out.append('    >>  ' + BLANK)
            out.append('    pt  %s' % esc(mc_pt.get(k)))
            out.append('    >>  ' + BLANK)
    out.append('```')
    out.append('')
rest = sorted(s for s in mc_idx if s not in placed)
if rest:
    out.append('## Everything else in this namespace')
    out.append('')
    out.append('```text')
    for s in rest:
        for suf in mc_idx[s]:
            k = s + suf
            out.append('  %s' % k)
            out.append('    en  %s' % esc(mc_en.get(k)))
            out.append('    >>  ' + BLANK)
            out.append('    pt  %s' % esc(mc_pt.get(k)))
            out.append('    >>  ' + BLANK)
    out.append('```')
txt = '\n'.join(out) + '\n'
with open(os.path.join(OUT, '99-slots-and-interface.md'), 'w', encoding='utf-8', newline='\n') as f:
    f.write(txt)
written.append(('99-slots-and-interface.md', 'Slots, world words and interface text',
                len(mc_idx), len(txt)))

# ------------------------------------------------- index
idx = ['# Index — every conversation MCA: Conversations ships', '',
       'Generated from the shipped data. Every question node, every button, every line, every '
       'per-personality override, with a blank under each one.', '',
       '**Start with [README.md](README.md).** It explains the shape of a conversation here and '
       'the rules the build enforces on anything you write.', '',
       '| File | Conversation | Nodes | Size |', '|---|---|---:|---:|']
for fn, title, n, size in written:
    idx.append('| [%s](%s) | %s | %d | %s KB |' % (fn, fn, title, n, format(size // 1024, ',')))
idx.append('')
idx.append('## Totals')
idx.append('')
idx.append('| | |')
idx.append('|---|---:|')
idx.append('| Question nodes | %d |' % len(questions))
idx.append('| Buttons the player can press | %d |' % sum(len(q.get('answers', [])) for q in questions.values()))
idx.append('| Outcomes behind those buttons | %d |'
           % sum(len(a.get('results', [])) for q in questions.values() for a in q.get('answers', [])))
idx.append('| English lines in `mca_dialogue` | %d |' % len(base_en))
idx.append('| Per-personality override lines | %d |' % sum(len(ov_en[p]) for p in PERS))
idx.append('| Personalities with an overlay | %d |' % len(PERS))
idx.append('| Catalog topics | %d |' % len(catalog))
idx.append('| Beat contracts | %d |' % len(beats))
idx.append('| Reply contracts | %d |' % len(replies))
idx.append('| Chat-mode intents | %d |' % len(intents))
with open(os.path.join(OUT, '00-INDEX.md'), 'w', encoding='utf-8', newline='\n') as f:
    f.write('\n'.join(idx) + '\n')

uncovered = [k for k in BASE_IDX if k not in shown and k not in set(leftover)]
print('coverage: base pools %d, shown in flow %d, in loose-lines %d, uncovered %d'
      % (len(BASE_IDX), len([k for k in BASE_IDX if k in shown]), len(leftover), len(uncovered)))
print('files', len(written) + 1, 'bytes', sum(w[3] for w in written))
for w in sorted(written, key=lambda x: -x[3])[:8]:
    print(w[0], w[3])

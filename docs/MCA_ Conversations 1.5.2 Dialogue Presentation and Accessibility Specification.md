# MCA: Conversations 1.5.2
## Dialogue Presentation, Classic UI, Minimal UI, Motion Controls, and UX Refinement Specification

**Repository:** `otectus/MCAConversations`  
**Target version:** `1.5.2`  
**Minecraft:** 1.20.1  
**Loader:** Forge 47.x  
**MCA Reborn:** `[7.6,8)`  
**Current mod version at time of specification:** `1.5.1`  
**Network protocol:** Remain `2`

---

# 1. Purpose

MCA: Conversations 1.5.2 should make the dialogue interface substantially more configurable without fragmenting the underlying conversation system.

The update should provide three clearly differentiated dialogue presentation choices:

1. **Current / Responsive**
   - The existing MCA: Conversations 1.5.1 responsive dialogue card.
   - Remains the default.
   - Preserves the responsive paging, number badges, keyboard controls, portrait, vanilla-menu graphics, narration, scrolling, and animation system.

2. **Minimal**
   - A new lightweight MCA: Conversations presentation.
   - Uses the same responsive layout, paging, keyboard navigation, synchronization, and accessibility systems.
   - Removes most decorative graphics and expensive presentation elements.
   - Intended for players who prefer a cleaner interface, use heavy modpacks, dislike visual clutter, or simply want dialogue to feel less intrusive.

3. **Original MCA**
   - Restores MCA Reborn's native dialogue presentation.
   - MCA: Conversations must cease replacing or suppressing MCA's question and answer rendering.
   - Conversation content and branching remain provided by MCA: Conversations, but the host mod owns presentation and mouse interaction again.

Motion must remain independently configurable:

- `FULL`
- `REDUCED`
- `OFF`

A player should therefore be able to use the current responsive card without animations, use the minimal card with or without motion, or opt out of the custom card entirely.

The update should be implemented as a **presentation-layer refinement only**. It must not alter dialogue selection, branching, affection changes, history, server authority, datapack semantics, or network packets.

---

# 2. Important Findings From the Current 1.5.1 Codebase

The implementation agent should begin from the current code rather than assuming these systems need to be created from scratch.

## 2.1 Original MCA presentation already exists as a fallback

`McaConversationsConfig.Client` currently defines:

```java
numberedResponses
numericResponseShortcuts
chatNumericShortcuts
showResponseControlHints
motionMode
uiSoundVolume
speakerNameAccent
showSpeakerPortrait
questionRevealMode
```

`ClientChoiceController.numberingEnabled()` reads `numberedResponses`.

`InteractScreenChoiceMixin.mcaconversations$active()` requires `numberingEnabled()`.

As a result:

```toml
numberedResponses = false
```

already stops the responsive card from taking ownership and preserves MCA's native mouse UI.

This is explicitly documented in the current `CONFIG.md`.

**1.5.2 must preserve this compatibility behavior.**

Do not replace it in a way that silently changes existing installations which already use `numberedResponses = false`.

---

## 2.2 Complete motion disabling already exists

The current:

```java
MotionMode {
    FULL,
    REDUCED,
    OFF
}
```

and `ConversationMotionSpec` already implement a proper zero-duration `OFF` profile.

The existing automated tests explicitly verify that `OFF` disables:

- card entrance motion
- row cascading
- focus transitions
- focus expansion
- selection press animation
- page motion
- exit fade

`QuestionReveal` is also bypassed while motion is `OFF`.

Therefore **do not add a redundant `disableAnimations` boolean**.

Instead, 1.5.2 should make the existing option more obvious in documentation and configuration comments:

```toml
motionMode = "OFF"
```

is the canonical way to completely disable dialogue animation.

Having two settings such as:

```toml
animations = false
motionMode = "FULL"
```

would create conflicting configuration states and should be avoided.

---

## 2.3 1.5.1 deliberately removed the old 1.5.0 visual-theme system

The 1.5.1 changelog records removal of:

- `enhancedConversationVisuals`
- `visualStyle`
- `panelOpacity`
- generated MCA: Conversations GUI texture sheets
- `generateUiTextures`
- `verifyGeneratedUiTextures`

The current responsive card instead uses:

- `options_background.png`
- `widgets.png`
- vanilla-style list selection borders
- vanilla-style scrollbar graphics
- vanilla yellow text emphasis

This design should remain intact for the default style.

### Critical migration rule

**Do not reintroduce a configuration key named `visualStyle`.**

Old 1.5.0 installations may still have stale values for that removed setting in historical configs or backups. Reusing that exact name for an unrelated 1.5.2 feature could accidentally reinterpret old configuration.

Use a new, semantically precise name such as:

```toml
dialogueMenuStyle = "RESPONSIVE"
```

---

# 3. User-Facing Design

## 3.1 New canonical style enum

Add a client-side enum:

```java
public enum DialogueMenuStyle {
    RESPONSIVE,
    MINIMAL,
    MCA_ORIGINAL
}
```

Recommended user-facing names:

| Config value | Display name | Meaning |
|---|---|---|
| `RESPONSIVE` | Current | Current 1.5.1 MCA: Conversations dialogue card |
| `MINIMAL` | Minimal | Simplified responsive interface |
| `MCA_ORIGINAL` | Original MCA | MCA Reborn's native dialogue menu |

Use `RESPONSIVE` rather than `VANILLA` because the current Conversations card already uses vanilla assets, while `MCA_ORIGINAL` also uses MCA's native presentation. Calling either simply "Vanilla" would be confusing.

---

# 4. Configuration

## 4.1 Add `dialogueMenuStyle`

Add under the existing client `[display]` section:

```toml
dialogueMenuStyle = "RESPONSIVE"
```

Default:

```text
RESPONSIVE
```

Suggested Forge config comment:

```text
Dialogue menu presentation.
RESPONSIVE  - the full MCA: Conversations responsive card.
MINIMAL     - the responsive menu with simpler, lower-overhead graphics.
MCA_ORIGINAL - let MCA Reborn draw and control its original dialogue menu.
```

---

# 5. Backward Compatibility With `numberedResponses`

Do **not** immediately remove `numberedResponses`.

Existing users may intentionally have:

```toml
numberedResponses = false
```

specifically because they want MCA's original interface.

Changing its meaning or removing it in 1.5.2 would silently restore the custom UI for those players.

## 5.1 Treat it as a deprecated compatibility override

Implement one authoritative resolver, preferably in `ClientChoiceController`:

```java
public static DialogueMenuStyle dialogueMenuStyle()
```

Its behavior should be:

```text
if numberedResponses == false:
    MCA_ORIGINAL
else:
    configured dialogueMenuStyle
```

Fallback on config read failure:

```text
RESPONSIVE
```

This provides safe migration.

### Effective behavior

| `numberedResponses` | `dialogueMenuStyle` | Effective result |
|---|---|---|
| `true` | `RESPONSIVE` | Responsive |
| `true` | `MINIMAL` | Minimal |
| `true` | `MCA_ORIGINAL` | Original MCA |
| `false` | anything | Original MCA |

Update the comment for `numberedResponses` to explain that it is retained for compatibility.

Example:

```text
Legacy compatibility switch from 1.4.x/1.5.1.
False always restores MCA's original dialogue UI.
New installations should normally leave this true and use dialogueMenuStyle instead.
```

Do not remove the option until a later release with an explicit migration strategy.

---

# 6. Refactor Configuration Access Around Effective Presentation

The rest of the client code should not repeatedly inspect raw Forge config values.

Add centralized helpers such as:

```java
DialogueMenuStyle dialogueMenuStyle()
boolean responsiveDialogueEnabled()
boolean minimalDialogueEnabled()
boolean originalMcaDialogueEnabled()
```

`numberingEnabled()` may remain temporarily as a compatibility alias if removing it would create unnecessary churn, but its meaning should become:

```java
return responsiveDialogueEnabled();
```

rather than reading `numberedResponses` directly.

This gives the rendering and mixin layers one authoritative interpretation of configuration.

---

# 7. Style 1: RESPONSIVE

`RESPONSIVE` must reproduce the 1.5.1 interface as closely as possible.

Treat visual regressions in this mode as bugs unless specifically required by the new abstraction.

It must retain:

- responsive central dialogue card
- vanilla dirt/menu background
- darker recessed answer list
- vanilla widget-texture number badges
- vanilla widget-texture paging buttons
- current focus frame
- current locked selection frame
- current scrollbar
- bold yellow speaker name
- optional villager portrait
- height-aware paging
- maximum nine keyboard-selectable responses per page
- multi-line wrapping
- oversized-response scrolling
- mouse navigation
- keyboard navigation
- number shortcuts
- narration
- control hints
- question reveal
- UI sounds
- motion profiles
- custom-font support
- GUI-scale handling
- resource-pack responsiveness

No old 1.5.0 theme assets should return.

---

# 8. Style 2: MINIMAL

## 8.1 Design goal

`MINIMAL` should not merely be a recolor of `RESPONSIVE`.

It should be a genuinely simpler presentation which retains the **functional improvements** of the Conversations UI while stripping away most decorative graphics.

The result should feel closer to a clean game overlay than a fully illustrated menu card.

This mode is especially useful for:

- players who dislike large UI elements
- accessibility users who prefer lower visual complexity
- small screens
- high GUI scale
- players using unusual resource packs
- lower-end systems
- large modpacks where entity rendering and GUI overdraw are undesirable
- players who want conversations to occupy less attention while still keeping keyboard navigation

---

# 9. Minimal Style Visual Rules

## 9.1 No tiled dirt background

Do not use `options_background.png` for the minimal panel.

Use a simple flat translucent backing.

Recommended appearance:

- dark neutral backing
- one-pixel border
- no gradient decoration
- no recessed dirt list area
- no decorative header surface

The exact ARGB constants may be adjusted after preview testing, but aim for approximately:

```text
background: dark neutral at ~70-80% opacity
border: muted gray
focused outline/fill: light gray
locked outline: white
primary text: white
secondary text: light gray
speaker accent: vanilla yellow
```

Do not reintroduce a configurable palette or opacity slider in 1.5.2.

The purpose is a stable simple preset, not another theming framework.

---

## 9.2 No widget-texture number badges

Instead of drawing every number through a nine-sliced vanilla button:

```text
[1]
[2]
[3]
```

or simply:

```text
1.
2.
3.
```

should be drawn directly.

Preferred default:

```text
1.
```

This preserves the compact typography already used internally while avoiding a textured button for every response.

The number column must remain dynamically sized from the active font.

Do not regress the current wide-font fix.

---

## 9.3 Simplified focus

A focused answer should use one lightweight treatment:

- one-pixel border, or
- subtle rectangular fill

Do not require:

- textured frame
- multiple nested frames
- decorative bevel
- elaborate selection chrome

Locked state must still be distinguishable from hover/focus.

Example:

```text
resting: no fill
focused: subtle gray fill
locked: white outline + subtle fill
```

Never use color alone as the only distinction.

---

## 9.4 Simplified paging controls

Do not use nine-sliced widget buttons.

Use compact controls such as:

```text
‹   Page 2/4   ›
```

or:

```text
<  2/4  >
```

They must retain real rectangular hit targets of at least the current usable dimensions.

A simpler drawing style must **not** mean smaller or harder-to-click hitboxes.

Unavailable directions should be visibly muted but remain geometrically stable.

---

## 9.5 Simplified scrollbar

Retain scrolling for oversized answers.

Use a simple flat track/thumb.

Do not remove overflow support in the name of simplicity.

---

# 10. Portrait Behavior in MINIMAL

Entity rendering is specifically called out by the current renderer as the most expensive operation on the card.

For that reason:

**MINIMAL should omit the live villager portrait.**

This is intentional rather than accidental.

Benefits:

- noticeably fewer render operations
- no entity rendering inside a GUI scissor
- no special entity-state failure path
- more horizontal text space
- visually quieter presentation
- clearer distinction from `RESPONSIVE`

The existing:

```toml
showSpeakerPortrait = true
```

should therefore mean:

> Show the portrait when the selected dialogue style supports one.

`RESPONSIVE` supports it.

`MINIMAL` does not.

`MCA_ORIGINAL` leaves presentation to MCA.

Update its documentation accordingly.

---

# 11. Speaker Name Styling

The existing:

```toml
speakerNameAccent = true
```

should continue to apply to both Conversations-rendered modes:

- `RESPONSIVE`
- `MINIMAL`

Do not attempt to inject the custom name style into `MCA_ORIGINAL`.

The purpose of Original MCA mode is to relinquish presentation ownership.

---

# 12. Motion Configuration

Keep the current:

```java
FULL
REDUCED
OFF
```

system.

This is already more useful than a boolean.

## 12.1 Required semantics

### FULL

Responsive:

- current card entrance
- row entry
- focus expansion/lift
- selection press
- page movement
- exit animation

Minimal:

- may retain subtle entrance/page movement
- may retain focus transition
- should avoid exaggerated pop-out effects that undermine the purpose of the minimal style

### REDUCED

Both Conversations-rendered styles:

- fades are permitted
- no spatial translation
- no row staggering
- no pop-out
- no lift
- no press movement

### OFF

Both Conversations-rendered styles:

- no entrance fade
- no entrance translation
- no row cascade
- no focus interpolation
- no focus enlargement
- no selection press animation
- no selection settle animation
- no page movement
- no page fade
- no exit fade
- no animated question reveal

Every visual state must resolve immediately.

`ConversationMotionSpec.instant()` should remain true.

---

# 13. Style-Aware Motion

The cleanest implementation is to let `ConversationMotionSpec` derive its values from:

```text
motion mode + dialogue style
```

For example:

```java
ConversationMotionSpec.current(DialogueMenuStyle style)
```

Recommended behavior:

| Style | FULL | REDUCED | OFF |
|---|---|---|---|
| RESPONSIVE | existing 1.5.1 motion | fades only | instant |
| MINIMAL | restrained motion | fades only | instant |
| MCA_ORIGINAL | not applicable | not applicable | not applicable |

For MINIMAL/FULL:

- card enter distance: approximately 1-2 px
- row stagger: preferably zero
- focus outset: preferably zero
- focus lift: zero
- selection press depth: zero
- short page transition acceptable

This keeps FULL from making the minimal interface behave like the full card.

---

# 14. Question Reveal

Existing:

```toml
questionRevealMode = "OFF"
```

or:

```toml
questionRevealMode = "FAST"
```

should continue to work in:

- RESPONSIVE
- MINIMAL

It should be ignored in:

- MCA_ORIGINAL

It must also continue to be forcibly bypassed whenever:

```toml
motionMode = "OFF"
```

Input must continue to finish the reveal instantly.

Never make the user wait for dialogue animation before they can choose a response.

---

# 15. Original MCA Style

## 15.1 Definition

`MCA_ORIGINAL` means MCA: Conversations stops replacing MCA Reborn's native dialogue UI.

This is not an approximation of MCA's old visual style.

Do not attempt to redraw something that merely looks similar.

Use the real MCA rendering path.

The existing `numberedResponses = false` behavior already proves this fallback route works.

---

# 16. `InteractScreenChoiceMixin` Requirements

The custom card currently suppresses MCA's question display while active:

```java
if (mcaconversations$active() || mcaconversations$renderer.hasOutgoingPresentation()) {
    ...
    dialogQuestionText = null;
}
```

For `MCA_ORIGINAL`:

- `mcaconversations$active()` must return `false`
- MCA's `dialogQuestionText` must never be suppressed
- MCA's original answer list remains visible
- custom renderer must not run
- custom mouse hit testing must not run
- custom wheel handling must not consume scrolling
- custom keyboard focus handling must not take ownership
- custom page logic must not replace MCA's own behavior
- custom outgoing animation must not briefly cover the original UI

The conversation system itself remains active.

Only presentation ownership changes.

---

# 17. Style Changes While a Dialogue Is Open

Handle this explicitly.

Changing:

```text
RESPONSIVE -> MINIMAL
```

while the screen is open should:

- preserve the active offer
- preserve absolute focused answer
- preserve current page where possible
- preserve selection lock
- invalidate the prepared visual model
- immediately rebuild using the new style

Changing:

```text
MINIMAL -> RESPONSIVE
```

should behave the same.

Changing either responsive style to:

```text
MCA_ORIGINAL
```

must:

- reset the custom renderer immediately
- cancel any outgoing card animation
- restore MCA's native question/answer rendering on the next frame
- not clear the server conversation
- not clear the current offer solely because presentation changed

Changing:

```text
MCA_ORIGINAL -> RESPONSIVE/MINIMAL
```

must safely build the current synchronized offer if one exists.

There should never be one frame where both UIs are clickable on top of each other.

---

# 18. Renderer Architecture

Avoid creating three copies of the dialogue renderer.

The important systems are already separated well:

- `DialoguePresentationBuilder`
- `DialogueChoiceLayout`
- `DialogueChoiceRenderer`
- `DialogueChoicePresenter`
- `DialogueChoiceVisualState`
- `DialogueCardSkin`
- `ConversationPalette`
- `ClientChoiceState`
- `DialogueChoiceNarrator`

The new styles should share everything that represents behavior.

## Shared between RESPONSIVE and MINIMAL

Both must use the same:

- synchronized offer
- translated answer components
- answer order
- wrapping
- page map
- hit targets
- focus state
- locked selection state
- keyboard controls
- pointer modality
- narration
- selection packet
- scrolling
- overflow handling

Only **presentation policy** should vary.

---

# 19. Recommended Skin Abstraction

The current static `DialogueCardSkin` assumes one visual implementation.

Refactor it rather than placing large style switches throughout `DialogueChoiceRenderer`.

Recommended structure:

```text
DialogueSkin
├── ResponsiveDialogueSkin
└── MinimalDialogueSkin
```

`MCA_ORIGINAL` does not need a skin because the Conversations renderer should not run.

A skin should own only visual primitives such as:

```java
panel(...)
row(...)
badge(...)
control(...)
portrait(...)
scrollbar(...)
```

The renderer should not care whether the panel is dirt-textured or flat.

One acceptable alternative is an enum-backed strategy if the implementation remains cleaner than multiple classes.

Do **not** put style branches in every individual draw call if they can be centralized.

---

# 20. Style Capabilities

Introduce an explicit presentation profile instead of scattering assumptions.

For example:

```java
record DialogueStyleProfile(
    boolean customRenderer,
    boolean portrait,
    boolean texturedBadges,
    boolean focusPopout
) {}
```

This does not need to become a public API.

It simply prevents logic such as:

```java
style != MINIMAL && style != MCA_ORIGINAL
```

from appearing repeatedly throughout the renderer.

Example profiles:

### RESPONSIVE

```text
customRenderer = true
portrait = true
texturedBadges = true
focusPopout = true
```

### MINIMAL

```text
customRenderer = true
portrait = false
texturedBadges = false
focusPopout = false
```

### MCA_ORIGINAL

```text
customRenderer = false
```

---

# 21. Layout

Do not build an entirely separate Minimal layout system.

The current `DialogueChoiceLayout` contains several hard-won fixes for:

- custom fonts
- narrow viewports
- small vertical space
- long questions
- large answers
- GUI scale
- page controls
- maximum nine shortcut rows
- portrait width
- footer reservation
- clipped answer rows

Reuse it.

Style-specific dimensions may be added where necessary, but there should remain one authoritative layout engine.

---

# 22. Distinguish Existing "Compact Layout" From MINIMAL Style

The current layout internally uses a `compact` boolean when the screen is too short to fit normal row spacing.

Do not conflate that with the new `MINIMAL` user-facing style.

These mean different things:

- **MINIMAL style:** user-selected visual presentation
- **compact layout:** emergency density mode chosen by layout because space is constrained

Consider renaming internal variables only if useful, but do not create ambiguous APIs such as:

```java
isCompact()
```

where a caller cannot tell whether it means visual style or responsive density.

---

# 23. Model Cache Invalidation

`DialogueChoiceRenderer.ModelKey` currently includes a `configSignature`.

The signature currently covers:

- motion mode
- hints
- speaker accent
- portrait setting
- question reveal

Add the **effective dialogue style**.

Any style option that changes wrapping or portrait reservation must invalidate the prepared model.

At minimum:

```text
effective style
motion mode
show hints
speaker accent
effective portrait visibility
question reveal
```

must participate in the appropriate cache invalidation.

A style change must never leave geometry from the previous skin attached to new rendering.

---

# 24. Portrait Cache Semantics

Change:

```java
wantsPortrait(speaker)
```

to consider both configuration and style.

Conceptually:

```java
return speaker != null
    && styleProfile.supportsPortrait()
    && ClientChoiceController.showSpeakerPortrait();
```

This also ensures MINIMAL gets the wider question area during preparation rather than merely failing to draw a portrait into space that was still reserved for it.

---

# 25. Input Handling

## RESPONSIVE and MINIMAL

Keep:

- mouse selection
- wheel
- Up
- Down
- Home
- End
- Enter
- Numpad Enter
- Space
- Page Up
- Page Down
- top-row digits
- Numpad digits

Do not create different controls by style.

---

# 26. Numeric Shortcuts and Classic Mode

GUI numeric shortcuts require the responsive page mapping.

Therefore:

```java
numericResponseShortcutsEnabled()
```

must return false when the effective style is `MCA_ORIGINAL`.

Do not invisibly intercept `1-9` while MCA's native menu is displayed.

The existing protection against stealing normal hotbar input must remain.

---

# 27. Chat Numeric Shortcuts

`chatNumericShortcuts` belongs to chat mode, not to the graphical dialogue card.

Currently `ClientChoiceController.chatShortcutsEnabled()` also depends on `numberingEnabled()`.

1.5.2 should consider correcting this coupling.

Recommended behavior:

```java
chatShortcutsEnabled()
    = chatNumericShortcuts
```

rather than:

```java
numberingEnabled() && chatNumericShortcuts
```

Choosing MCA's original GUI should not unexpectedly remove numeric reply selection from an entirely separate ChatScreen frontend.

Add a regression test if this change is made.

---

# 28. Townstead Compatibility

Townstead remains optional and must remain statically unlinked.

Do not allow the new enum to introduce a compile-time Townstead dependency.

### RESPONSIVE

Retain current Townstead numbered-choice support.

### MINIMAL

Townstead owns its own RPG dialogue presentation.

Do not attempt to replace the entire Townstead screen with the Minimal Conversations skin.

Its existing optional number-badge/selection bridge may continue to operate.

### MCA_ORIGINAL

Interpret this as **host-native presentation** for compatibility screens as well.

For Townstead:

- Townstead keeps its own interface
- do not add Conversations visual overlays merely because the base MCA style is Original
- avoid invisible GUI numeric mappings

The existing `@Pseudo` and soft-failing compatibility approach must remain.

---

# 29. Narration

RESPONSIVE and MINIMAL must retain existing narration behavior:

- offer count
- focus change
- page change
- selection
- offer expiry
- pointer dwell behavior

MINIMAL must not lose narration simply because it has fewer graphics.

`MCA_ORIGINAL` should not cause the custom renderer to duplicate narration over MCA's native screen.

Do not have both systems announce the same focused response.

---

# 30. UI Sounds

RESPONSIVE and MINIMAL should continue to honor:

```toml
uiSoundVolume
```

For MINIMAL it is acceptable to reduce unnecessary focus sound frequency if testing shows it feels disproportionately noisy, but do not create another sound-volume setting.

`0.0` must remain a complete sound disable.

`MCA_ORIGINAL` should leave normal interaction sound ownership to MCA.

---

# 31. Accessibility Requirements

The style system should be treated as an accessibility feature as much as a cosmetic one.

## Required guarantees

### Motion

`OFF` means literally no animation.

### Color

Focus must not be indicated by color alone.

### Font

All styles must work with:

- vanilla font
- Unicode fallback
- wider resource-pack fonts
- taller fonts

### Scaling

Test:

- GUI scale 1
- GUI scale 2
- GUI scale 3
- GUI scale 4
- narrow minimum viewports

### Long text

All Conversations-rendered styles must support:

- multi-line questions
- multi-line answers
- more than nine responses
- oversized single answers
- scrolling
- page navigation

### Input

Animations must never delay selection.

### Minimal UI

Minimal must remain readable enough that "simpler" does not mean "lower contrast."

---

# 32. Performance Goals

MINIMAL should have a measurable reduction in rendering complexity.

At minimum it should avoid:

- tiled dirt blits
- nine-sliced badges on every row
- nine-sliced page buttons
- live villager entity rendering
- decorative gradients
- focus geometry expansion if practical

Prefer:

- a handful of `fill()` calls
- normal text draws
- a simple scrollbar
- simple outlines

Do not create allocations every frame merely to support style selection.

Resolve the effective style once per render/model decision and reuse it.

---

# 33. Do Not Reintroduce Custom Texture Generation

1.5.2 must not restore:

- generated dialogue texture sheets
- Gradle texture-generation tasks
- old palette assets

RESPONSIVE should continue to use Minecraft's existing GUI assets.

MINIMAL should preferably be texture-free.

This means the new style feature should not increase the asset maintenance burden introduced and then deliberately removed in 1.5.1.

---

# 34. Resource Pack Behavior

## RESPONSIVE

Must remain resource-pack aware through Minecraft's GUI textures.

If a resource pack changes vanilla menu/button art, the current card should continue following it.

## MINIMAL

Because it uses flat primitives, it should intentionally be mostly independent of GUI textures.

It should still honor:

- active font
- language
- text styling

## MCA_ORIGINAL

Whatever MCA and the active resource pack normally do.

Document this distinction.

---

# 35. Development Preview Improvements

The existing development-only:

```text
/mcaconversations-preview
```

is ideal for this feature.

Extend it.

Recommended controls:

```text
S = cycle dialogue style
M = cycle motion mode
H = toggle hints
P = toggle portrait
R = reload fixture
[ / ] = previous/next fixture
```

The preview legend should display:

```text
style RESPONSIVE
motion FULL
portrait ON
hints ON
```

When cycling to `MCA_ORIGINAL`, the preview harness cannot truly reproduce MCA's native `InteractScreen` because MCA cannot load in the development runtime.

Therefore either:

1. display a clear preview message:

```text
MCA_ORIGINAL uses MCA's native screen and is not rendered by this preview.
```

or

2. skip that style in the graphical preview cycle.

Do not build a fake MCA screen solely for the preview tool.

---

# 36. Suggested Client Configuration Documentation

Update the `[display]` table in `CONFIG.md`.

Recommended structure:

| Option | Default | Meaning |
|---|---|---|
| `dialogueMenuStyle` | `RESPONSIVE` | `RESPONSIVE` uses the current Conversations card, `MINIMAL` uses a simplified responsive interface, `MCA_ORIGINAL` leaves the dialogue UI to MCA Reborn |
| `numberedResponses` | `true` | Legacy compatibility switch; `false` always restores MCA's original dialogue menu |
| `numericResponseShortcuts` | `true` | Enables digits on Conversations-rendered dialogue menus |
| `chatNumericShortcuts` | `true` | Enables numeric selection in an empty ChatScreen when chat replies are pending |
| `showResponseControlHints` | `true` | Shows keyboard/paging hints on Conversations-rendered menus |
| `motionMode` | `FULL` | `FULL` animates normally, `REDUCED` uses fades only, `OFF` disables all dialogue animation |
| `uiSoundVolume` | `0.65` | Volume for Conversations response UI sounds; `0` disables them |
| `speakerNameAccent` | `true` | Bold/yellow unambiguous speaker name on Conversations-rendered menus |
| `showSpeakerPortrait` | `true` | Shows the portrait where supported; the Minimal style intentionally omits it |
| `questionRevealMode` | `OFF` | Optional fast line reveal; ignored with motion `OFF` and in MCA Original mode |

Explicitly tell users:

> For the exact MCA Reborn dialogue interface, use `dialogueMenuStyle = "MCA_ORIGINAL"`.

> To keep the new interface but remove animations, use `motionMode = "OFF"`.

These are likely to be the two most common support questions after release.

---

# 37. Localization

Add English and Portuguese-Brazilian parity for any new display strings.

Suggested keys:

```text
gui.mcaconversations.responses.style.responsive
gui.mcaconversations.responses.style.minimal
gui.mcaconversations.responses.style.mca_original
```

English:

```text
Current
Minimal
Original MCA
```

Portuguese-Brazilian translations should be added by the implementation agent consistently with the rest of the locale.

If configuration enum values are only represented in TOML and never rendered, these keys may still be useful for the development preview and future config-screen integration.

Maintain locale parity tests.

---

# 38. Configuration Migration

## Existing 1.5.1 user

Example:

```toml
numberedResponses = true
motionMode = "FULL"
```

Result after upgrade:

```text
RESPONSIVE + FULL
```

No visible behavior change.

---

## Existing 1.5.1 user with native MCA UI

```toml
numberedResponses = false
```

Must remain:

```text
MCA_ORIGINAL
```

even if the newly generated enum defaults to `RESPONSIVE`.

This is the main reason the compatibility override is required.

---

## Existing user with motion disabled

```toml
motionMode = "OFF"
```

Must remain completely motionless.

---

## Historical 1.5.0 config

Do not interpret stale:

```text
visualStyle
enhancedConversationVisuals
panelOpacity
```

as 1.5.2 settings.

Continue allowing Forge to correct/remove them normally.

---

# 39. Potential Future Cleanup

Do not perform this cleanup in 1.5.2 unless migration can be guaranteed.

A later release could remove `numberedResponses` once:

- `dialogueMenuStyle` has existed for at least one stable release
- users have had time to migrate
- a proper raw-config migration exists

For 1.5.2, compatibility is more important than achieving a perfectly minimal TOML.

---

# 40. Required Code Areas

The coding agent should inspect and expect changes in at least:

```text
src/main/java/dev/otectus/mcaconversations/McaConversationsConfig.java

src/main/java/dev/otectus/mcaconversations/client/dialogue/
    ClientChoiceController.java
    ConversationMotionSpec.java
    DialogueChoiceRenderer.java
    DialogueCardSkin.java
    DialogueChoiceLayout.java
    DialoguePresentationBuilder.java
    PreparedDialogueCard.java

src/main/java/dev/otectus/mcaconversations/mixin/client/
    InteractScreenChoiceMixin.java

src/main/java/dev/otectus/mcaconversations/client/dialogue/dev/
    DialogueCardPreviewScreen.java

src/main/resources/assets/mcaconversations/lang/
    en_us.json
    pt_br.json

src/test/java/dev/otectus/mcaconversations/
    ConfigSpecTest.java

src/test/java/dev/otectus/mcaconversations/client/dialogue/
    DialogueChoiceLayoutTest.java
    DialogueRenderPlanTest.java
    DialogueChoiceVisualStateTest.java
    DialogueCardSkinTest.java
```

Potentially introduce:

```text
DialogueSkin.java
ResponsiveDialogueSkin.java
MinimalDialogueSkin.java
DialogueStyleProfile.java
```

Exact class names are flexible, but presentation policy must remain isolated from conversation truth.

---

# 41. Configuration Tests

Add tests verifying:

1. `dialogueMenuStyle` exists in the client spec.
2. Default is `RESPONSIVE`.
3. It does not exist in common/server specs.
4. `numberedResponses=false` resolves to `MCA_ORIGINAL`.
5. `numberedResponses=true + RESPONSIVE` resolves to responsive.
6. `numberedResponses=true + MINIMAL` resolves to minimal.
7. `numberedResponses=true + MCA_ORIGINAL` resolves to original.
8. Failed/unloaded client config reads safely default to responsive presentation.
9. No server or gameplay decision depends on the client style.

---

# 42. Motion Tests

Retain every current `DialogueChoiceVisualStateTest`.

Extend them where style-dependent specs are introduced.

For each responsive style assert:

### OFF

Every duration:

```text
0
```

Every movement:

```text
0
```

`instant()`:

```text
true
```

### REDUCED

- no translation
- no row stagger
- no focus outset
- no lift
- fades permitted

### MINIMAL FULL

Assert the chosen minimal-motion contract, especially:

- no focus pop-out if that is part of the profile
- no row cascade if intentionally removed
- no selection-press movement if intentionally removed

---

# 43. Render-Plan Tests

Run the existing viewport/font matrix against both Conversations-rendered styles.

At minimum verify:

- panel inside screen
- rows inside panel
- no row overlap
- positive answer width
- page controls inside panel
- no badge/text overlap
- clipped answer scrollbar stays inside row
- question never pushes responses off-screen
- nine-response shortcut maximum remains intact

MINIMAL must not get weaker layout coverage simply because it draws fewer graphics.

---

# 44. Classic-Mode Regression Tests

The critical behavior is not how it looks, but that Conversations stops interfering.

Where practical, test or probe that `MCA_ORIGINAL` causes:

```text
custom renderer inactive
legacy MCA question not suppressed
custom mouse interception inactive
custom wheel interception inactive
custom GUI numeric interception inactive
```

Update `MixinTargetProbeTest` or add a focused presentation-mode test if appropriate.

Because MCA's development runtime situation prevents normal visual integration testing, this logic deserves explicit automated coverage.

---

# 45. Hot-Style Switching Tests

If client config mutation can occur while the preview is open, test:

```text
RESPONSIVE -> MINIMAL
MINIMAL -> RESPONSIVE
RESPONSIVE -> MCA_ORIGINAL
MCA_ORIGINAL -> RESPONSIVE
```

Verify no mutation to:

- offer revision
- answer ordering
- selected absolute index
- conversation server state

Presentation must never become conversation truth.

---

# 46. Optional Compatibility Tests

Continue passing:

- `NoMcaStaticLinkTest`
- `NoTownsteadStaticLinkTest`
- `MixinTargetProbeTest`
- Townstead UI mixin probes
- dedicated-server safety tests
- locale parity tests

Adding client presentation settings must not accidentally cause client classes to initialize on a dedicated server.

---

# 47. Acceptance Matrix

The release is not complete until this matrix works.

| Style | Motion | Expected |
|---|---|---|
| RESPONSIVE | FULL | Exact current-style card with full interaction motion |
| RESPONSIVE | REDUCED | Same card, fades only |
| RESPONSIVE | OFF | Same card, instantaneous |
| MINIMAL | FULL | Lightweight card with restrained motion |
| MINIMAL | REDUCED | Lightweight card, fades only |
| MINIMAL | OFF | Lightweight card, instantaneous |
| MCA_ORIGINAL | FULL | MCA native UI, motion setting ignored |
| MCA_ORIGINAL | REDUCED | MCA native UI, motion setting ignored |
| MCA_ORIGINAL | OFF | MCA native UI, motion setting ignored |

Also test each meaningful row with:

- hints on/off
- portrait on/off where supported
- speaker accent on/off
- short question
- long question
- one answer
- nine answers
- more than nine answers
- long answer requiring scrolling
- mouse input
- keyboard input
- custom font
- GUI scale 4

---

# 48. Additional UX Refinements Worth Including

These are small, considerate improvements that fit naturally into 1.5.2.

## 48.1 Make configuration terminology user-centered

Current `numberedResponses` describes an implementation detail rather than what the user is choosing.

The new canonical option should talk about the **dialogue menu**, not renderer internals.

This will reduce support confusion.

---

## 48.2 Explain ignored settings

Config documentation should explicitly state:

### In `MCA_ORIGINAL`

Ignored:

- `motionMode`
- `showResponseControlHints`
- `showSpeakerPortrait`
- `questionRevealMode`
- `speakerNameAccent` for the MCA screen
- GUI `numericResponseShortcuts`

MCA owns those aspects.

### In `MINIMAL`

Ignored:

- live portrait rendering

Everything else remains meaningful.

Do not silently let players wonder whether their configuration is broken.

---

## 48.3 Avoid style-dependent gameplay behavior

No style may affect:

- which responses exist
- response ordering
- checks
- hearts
- dispositions
- history
- quest hooks
- reputation hooks
- gossip
- session state
- server timing
- answer validation

Style selection is strictly local client presentation.

---

## 48.4 Preserve absolute response identity

The same synchronized answer index must mean the same thing regardless of style.

A player switching from Responsive to Minimal must never cause:

```text
visible button 2
```

to point to a different server answer.

All responsive styles must continue using `absoluteIndex`.

---

## 48.5 Never make Minimal a "low quality" mode

Minimal should look deliberate.

Avoid:

- missing padding
- tiny hitboxes
- poor contrast
- uncentered text
- clipped numbers
- arbitrary magic coordinates

"Simple" should describe visual complexity, not implementation quality.

---

# 49. Non-Goals

Do not include the following in 1.5.2:

- new conversation content
- new server packets
- new dialogue conditions
- new heart mechanics
- new generated GUI textures
- configurable custom RGB themes
- another `panelOpacity` option
- animation-speed sliders
- arbitrary per-animation timing configuration
- replacing Townstead's entire dialogue renderer
- reproducing MCA's original UI manually
- server-controlled player UI style
- forcing one style on multiplayer clients

---

# 50. Compatibility Contract

1.5.2 must remain compatible with 1.5.1 worlds.

No migration should be necessary for:

- saves
- datapacks
- conversation history
- dispositions
- gossip
- quest integration
- reputation integration
- Townstead state

The new options belong exclusively in:

```text
config/mcaconversations-client.toml
```

They are local player preferences.

A multiplayer server should neither know nor care which presentation a player chose.

---

# 51. Network Protocol

Keep:

```text
protocol = 2
```

No new packet is justified.

The server already sends the authoritative response offer.

The client already submits revisioned absolute response indices.

The same offer can be rendered by any local skin.

Do not transmit:

```text
dialogue style
motion mode
portrait setting
visual preferences
```

to the server.

---

# 52. Version and Documentation Updates

Update:

```properties
mod_version=1.5.2
```

Update:

- `CHANGELOG.md`
- `CONFIG.md`
- README configuration section if applicable
- CurseForge-facing documentation if it lists client UI configuration
- English locale
- Portuguese-Brazilian locale

No network compatibility warning is required.

---

# 53. Suggested 1.5.2 Changelog Structure

## Added

- Three dialogue presentation choices: Current, Minimal, and Original MCA.
- Minimal presentation using the responsive Conversations interaction system with substantially simpler graphics and no live portrait.
- Explicit `dialogueMenuStyle` client configuration.

## Changed

- Original MCA presentation is now a first-class documented option rather than being discoverable only through `numberedResponses=false`.
- `motionMode=OFF` is documented as the definitive complete animation disable.
- Client presentation configuration is resolved centrally so styles cannot disagree about input ownership.
- Minimal presentation uses restrained motion under Full mode.

## Compatibility

- Existing `numberedResponses=false` configs continue restoring MCA's native dialogue UI.
- Existing `motionMode` values retain their exact meaning.
- Protocol remains `2`.
- Saves, datapacks, and server configuration are unchanged.

---

# 54. Definition of Done

1.5.2 is complete only when all of the following are true:

- [ ] `dialogueMenuStyle` exists in the client configuration.
- [ ] Default style reproduces 1.5.1.
- [ ] `MCA_ORIGINAL` uses the real MCA interface rather than an imitation.
- [ ] Existing `numberedResponses=false` users remain on the MCA interface automatically.
- [ ] MINIMAL is visually distinct and substantially simpler than RESPONSIVE.
- [ ] MINIMAL retains responsive paging and input functionality.
- [ ] MINIMAL does not render a live entity portrait.
- [ ] `motionMode=OFF` disables every Conversations-owned animation.
- [ ] `motionMode=REDUCED` contains no spatial motion.
- [ ] Question reveal does not run under motion OFF.
- [ ] Original MCA mode does not suppress MCA question/answer rendering.
- [ ] Original MCA mode does not intercept custom GUI number shortcuts.
- [ ] Switching style cannot change answer identity or server state.
- [ ] Current 1.5.1 layout behavior remains intact in RESPONSIVE.
- [ ] Long answers remain scrollable.
- [ ] More than nine answers remain pageable.
- [ ] Custom fonts remain supported.
- [ ] GUI scale and small-screen tests pass.
- [ ] Narration remains functional in Conversations-rendered styles.
- [ ] No duplicate narration occurs in Original MCA mode.
- [ ] Townstead remains optional and statically unlinked.
- [ ] No new GUI texture assets are generated.
- [ ] Old 1.5.0 `visualStyle` is not reused.
- [ ] English and Portuguese-Brazilian localization remain in parity.
- [ ] Existing client/server/common configuration tests pass.
- [ ] Network protocol remains `2`.
- [ ] `mod_version` becomes `1.5.2`.

---

# 55. Implementation Principle

The central rule for this update is:

> **One conversation system, one input/state model, multiple presentations.**

Do not create a "Minimal conversation system" or a "Classic conversation system."

The server decides what can be said.

`ClientChoiceState` decides which synchronized offer is locally active.

The presentation layer decides only how that truth appears.

For `RESPONSIVE` and `MINIMAL`, MCA: Conversations owns that presentation.

For `MCA_ORIGINAL`, it deliberately steps aside and lets MCA Reborn own it.

That separation should make 1.5.2 easier to maintain while giving players substantially more control over how conversations feel.
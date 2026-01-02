
## [1.0.6] - TBA

[View changes between 1.0.5 with 1.0.6](https://github.com/tracystacktrace/ICY/compare/1.0.5...1.0.6)

- Fixed gunpowder fuse block information output
- Added item frames description (shows a display name of hanged item)
- Correct icons for pillars, logs and other "pillar" like blocks in tooltip
- Fixed a combustion block's name being empty

## [1.0.5] - 2025-07-21

[View changes between 1.0.4 with 1.0.5](https://github.com/tracystacktrace/ICY/compare/1.0.4...1.0.5)

- Small micro optimizations of general render and fetch code.
- When a mob's display ItemStack is empty, the plaque will not render this space.
- Additional null checks against crashing when getting data from TileEntity.

## [1.0.4] - 2025-07-08

[View changes between 1.0.3 with 1.0.4](https://github.com/tracystacktrace/ICY/compare/1.0.3...1.0.4)

- Updated toolchain to FoxLoader `2.0-alpha32` (2.9_03)
- Instead of dirty mixing, using `GuiConfigProviderConfigObject` for config menu handler
- Fixed nether portal name in tooltip
- Added basic entity tooltip that can show: name, nature, source and entity id (if id/meta enabled in configs)

## [1.0.3] - 2025-06-27

[View changes between 1.0.2 with 1.0.3](https://github.com/tracystacktrace/ICY/compare/1.0.2...1.0.3)

- Fixed a bug tooltip location when focused on blocks with active stats present
- Fixed original pumpking carving description name
- Fixed wall hanged skulls names in tooltip
- Fixed basalt block name in tooltip
- Fixed prickly pear name in tooltip
- Fixed a bug where offset sliders would literally slide values
- Added honeycomb block passive info

## [1.0.2] - 2025-06-22

[View changes between 1.0.1 with 1.0.2](https://github.com/tracystacktrace/ICY/compare/1.0.1...1.0.2)

Briefcase update! Updated some branding elements, including big logo!

**Internal changes:**
- Added a separate, a little bit advanced caching for this mod
- Internal GUI code changes, the mod now uses [HelloGUI](https://github.com/tracystacktrace/HelloGUI) for dynamic config GUIs
- Added a nicer and more advanced API for this mod. I can easily add my own additional description for specific items, so you do
- Deleted an old event "based" description collector. Use other method provided in readme.md

**Visible changes:**
- Now you can see ID/Metadata of the hovered block. Open config screen to turn on it
- Added settings to change X and Y offsets of a hint ui
- Added additional description for following blocks:
  - Pistons (both standard and sticky)
  - Magma blocks (except for kottamagma)
  - Gears
  - Carved pumpkins and watermelons
  - Dungeon chest
  - Cake and pie
  - Wait block
  - Bonsai planter
  - Cauldron (WIP)
  - Forge
  - Refridgifreezer
  - Furnace
  - Incinerator
For some blocks, you need to press **SHIFT** to see additional description!

## [1.0.1] - 2025-06-

[View changes between 1.0 with 1.0.1](https://github.com/tracystacktrace/ICY/compare/1.0...1.0.1)

- Small bugfix: includes all item blocks that weren't added in version 1.0 

## [1.0] - 2025-06-13
- Initial release

## [1.0.5] - TBA

- Small micro optimizations of general render and fetch code.
- When a mob's display ItemStack is empty, the plaque will not render this space.
- Additional null checks against crashing when getting data from TileEntity.

## [1.0.4] - 2025-07-08

- Updated toolchain to FoxLoader `2.0-alpha32` (2.9_03)
- Instead of dirty mixing, using `GuiConfigProviderConfigObject` for config menu handler
- Fixed nether portal name in tooltip
- Added basic entity tooltip that can show: name, nature, source and entity id (if id/meta enabled in configs)

## [1.0.3] - 2025-06-27
- Fixed a bug tooltip location when focused on blocks with active stats present
- Fixed original pumpking carving description name
- Fixed wall hanged skulls names in tooltip
- Fixed basalt block name in tooltip
- Fixed prickly pear name in tooltip
- Fixed a bug where offset sliders would literally slide values
- Added honeycomb block passive info

## [1.0.2] - 2025-06-22
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

## [1.0.1] - 2025-06-13
- Small bugfix: includes all item blocks that weren't added in version 1.0 

## [1.0] - 2025-06-13
- Initial release
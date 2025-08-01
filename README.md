![ICY - Integrated Contextualization Yield-Software](https://github.com/tracystacktrace/ICY/raw/main/docs/icy_big_logo.png)

---

<div align="center">

[![Available on - Modrinth](https://img.shields.io/badge/Available_on-Modrinth-4bab62?logo=modrinth&logoColor=white)](https://modrinth.com/mod/icy-rind) [![Running on - FoxLoader](https://img.shields.io/badge/Running_on-FoxLoader-orange)](https://github.com/Fox2Code/FoxLoader) [![GitHub Release](https://img.shields.io/github/release/tracystacktrace/ICY?include_prereleases=&sort=semver&color=142B36)](https://github.com/tracystacktrace/ICY/releases/) [![License](https://img.shields.io/badge/License-Apache--2.0_license-blue)](#license)

[![Mod Thread - ReIndev Discord](https://img.shields.io/badge/Mod_Thread-ReIndev_Discord-5A66F1?style=for-the-badge&logo=discord&logoColor=white)](https://discord.com/channels/870388843076005950/1383151049027620946)
</div>

A FoxLoader (ReIndev mod loader) client-side modification that adds a handy tooltip which reveals some info about the block you aim with your cursor.

Basically, the nearest mod that come to my mind is `WAILA`, but this mod is specifically designed for `ReIndev` mechanics and environment.

At this moment, the mod shows such information:
- Basic information (block name, id/meta if enabled, mod source)
- Specific information for each block individually:
	- Honeycomb: empty/filled status
	- Pistons (+ sticky variant): enabled/disabled status
	- Gears: power value
	- Carved Pumpkins and Watermelons: carving type
	- Dungeon Chest: loot status
	- Cake and Pie blocks: pieces left
	- Wait Block: wait delay
	- Bonsai Planter: planted tree type and growth information
	- Cauldron: potion information
	- Forge, Refridgifreezer, Furnace, Incinerator: progress and inventory contents

**Attention!** For some blocks, such as `Bonsai Planter`, `Cauldron`, `Forge`, `Refridgifreezer`, `Furnace` and `Incinerator`, you need to hold `SHIFT` key to see the details!


![Showcase](https://github.com/tracystacktrace/ICY/raw/main/docs/showcase_2.png)

![Showcase](https://github.com/tracystacktrace/ICY/raw/main/docs/showcase_3.png)

![Showcase](https://github.com/tracystacktrace/ICY/raw/main/docs/showcase_4.png)

![Showcase](https://github.com/tracystacktrace/ICY/raw/main/docs/showcase_5.png)

## Configuration

The tooltip can be easily configured and has own menu, just follow `Mods` -> `ICY` -> `Configure mod`.

Current tweakable options include:
- Show/hide ID and metadata of a block
- Show/hide harvestability icon
- Use either static color or gradient of two colors (ARGB) for tooltip background
- Change X/Y offset location of the tooltip
- Change the global location (anchor) of the tooltip

## Installation

Simply grab a `.jar` file from releases and put into `mods` folder of your instance!

Want to compile by yourself? Just download the sources and run the following command:
```shell
./gradlew build
```

The output file will be located in `build/libs` folder.

## Plugin Development

### Basic Theory

Since version `1.0.2`, ICY supports handling additional description resolvers that are outside the mod.

**First of all, understand the basic theory!** There are **TWO** types of resolvers: **PASSIVE** and **ACTIVE**.
- **PASSIVE** resolvers are called only once when the hint UI has been moved to another block, or a block state **CHANGED HEAVILY** (id/meta) by game.
- **ACTIVE** resolvers are called every "render tick" before showing data to player. It's useful for showing `TileEntity` states and things that aren't globally changed.

If you want to add additional description based on mostly id and/or meta, it's recommended to mark your resolver as **PASSIVE**. However, if it checks the TileEntity, some hidden or external factors and values, tag it as **ACTIVE**.

### Getting to Code

Firstly, you need to create a class that implements [`IResolver`](https://github.com/tracystacktrace/ICY/blob/main/src/main/java/net/tracystacktrace/icy/resolver/IResolver.java), something like this:

```java
import net.tracystacktrace.icy.resolver.IResolver;

/*
        In this demonstration, let's assume we have block we need to put specific strings on:
        MyMod.COOL_BLOCK_A
        
        The CoolBlockA.class contains a method that returns a specific string that we will add to hint UI:
        CoolBlockA.getFunnyString(int x, int y, int z, int meta)
 */

public class MyOwnResolver implements IResolver {
    
    @Override
    public boolean passes(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    ) {
        //let's just check by ID, but you can use all provided arguments for check
        //if you want to reference to world, use Minecraft.getInstance().theWorld
        return block.blockID == MyMod.COOL_BLOCK_A.blockID;
    }
    
    @Override
    public String @NotNull [] bake(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    ) {
        //here, you either get a constant field or cast the block instance
        CoolBlockA my_block = (CoolBlockA) block;
        //"bake" and return strings
        return new String[] {
                "Hello, world!",
                my_block.getFunnyString(x, y, z, meta)
        };
    }
}
```

Now, as you created the `MyOwnResolver` class, it's time to add it to ICY!

For this, open the class that extends `Mod` and call `ICYResolver.addPassiveResolver`/`ICYResolver.addActiveResolver` methods depending on your situation **INSIDE** `onPostInit` method:
```java
import net.tracystacktrace.icy.client.ICYResolver;

public class MyMod extends Mod {
    /* ... */
    
    @Override
    public void onPostInit() {
        /* some other code */

        //adds the newly created and marks it as "passive"
        ICYResolver.addPassiveResolver(new MyOwnResolver());
        
        //however, if you want to add it as an "active" resolver, use "addActiveResolver"
        ICYResolver.addActiveResolver(new MyOwnResolver());
    }
}
```

That's all! Your resolver is now registered in ICY and will be used!

## License

This mod is licensed under [Apache License 2.0](https://github.com/tracystacktrace/ICY/blob/main/LICENSE)

The library `HelloGUI` is licensed under [GNU Lesser General Public License v2.1](https://github.com/tracystacktrace/HelloGUI/blob/main/LICENSE_HELLOGUI)

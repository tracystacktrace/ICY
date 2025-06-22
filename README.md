![ICY - Integrated Contextualization Yield-Software](https://github.com/tracystacktrace/ICY/raw/main/docs/icy_big_logo.png)

---

<div align="center">

[![Running on - FoxLoader](https://img.shields.io/badge/Running_on-FoxLoader-orange)](https://github.com/Fox2Code/FoxLoader) [![GitHub Release](https://img.shields.io/github/release/tracystacktrace/ICY?include_prereleases=&sort=semver&color=142B36)](https://github.com/tracystacktrace/ICY/releases/) [![License](https://img.shields.io/badge/License-Apache--2.0_license-blue)](#license)
</div>

Mod that adds a handy tooltip that reveals some info about the block you aim with your cursor!

The 1.0 version is the first release with basic features. However, I'm planning to expand this mod in the future.

You can contribute or provide suggestions for new features!

![Showcase](https://github.com/tracystacktrace/ICY/raw/main/docs/showcase_1.png)

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
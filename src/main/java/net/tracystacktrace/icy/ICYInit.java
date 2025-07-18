package net.tracystacktrace.icy;

import com.fox2code.foxloader.config.ConfigIO;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.loader.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.tracystacktrace.icy.client.ICYRenderer;
import net.tracystacktrace.icy.client.ICYResolver;
import net.tracystacktrace.icy.resolver.active.BonsaiPlanterResolver;
import net.tracystacktrace.icy.resolver.active.CauldronResolver;
import net.tracystacktrace.icy.resolver.active.cooking.ForgeResolver;
import net.tracystacktrace.icy.resolver.active.cooking.FurnaceResolver;
import net.tracystacktrace.icy.resolver.active.cooking.IncineratorResolver;
import net.tracystacktrace.icy.resolver.active.cooking.RefridgifreezerResolver;
import net.tracystacktrace.icy.resolver.passive.*;
import org.jetbrains.annotations.ApiStatus;
import org.lwjgl.input.Keyboard;

public class ICYInit extends Mod {
    public static final ICYRenderer RENDERER = new ICYRenderer();
    public static final ICYConfig CONFIG = new ICYConfig();
    private static ModContainer INTERNAL_CONTAINER;

    @Override
    public void onInit() {
        INTERNAL_CONTAINER = this.getModContainer();
        this.setConfigObject(CONFIG);

        /* init passive resolvers */
        ICYResolver.addPassiveResolver(new GrowableResolver());
        ICYResolver.addPassiveResolver(new MagmaResolver());
        ICYResolver.addPassiveResolver(new DungeonChestResolver());
        ICYResolver.addPassiveResolver(new CarvingResolver());
        ICYResolver.addPassiveResolver(new YummyBlockResolver());
        ICYResolver.addPassiveResolver(new PistonPowerResolver());
        ICYResolver.addPassiveResolver(new GearPowerResolver());
        ICYResolver.addPassiveResolver(new WaitBlockResolver());
        ICYResolver.addPassiveResolver(new HoneycombResolver());

        /* init active resolvers */
        ICYResolver.addActiveResolver(new BonsaiPlanterResolver());
        ICYResolver.addActiveResolver(new CauldronResolver());
        ICYResolver.addActiveResolver(new IncineratorResolver());
        ICYResolver.addActiveResolver(new FurnaceResolver());
        ICYResolver.addActiveResolver(new RefridgifreezerResolver());
        ICYResolver.addActiveResolver(new ForgeResolver());
    }

    @ApiStatus.Internal
    public static void forceSaveConfig() {
        ConfigIO.writeConfiguration(INTERNAL_CONTAINER, CONFIG);
    }

    @ApiStatus.Internal
    public static int getLargestString(FontRenderer fontRenderer, String[] strings) {
        if (strings == null || strings.length == 0) {
            return 0;
        }

        int result = 0;
        for (String s : strings) {
            result = Math.max(result, fontRenderer.getStringWidth(s));
        }
        return result;
    }

    @ApiStatus.Internal
    public static boolean showActiveCache() {
        return Minecraft.getInstance().currentScreen == null &&
                (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
    }
}

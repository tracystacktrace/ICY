package net.tracystacktrace.icy;

import com.fox2code.foxloader.config.ConfigEntry;

public class ICYConfig {

    @ConfigEntry
    public boolean enable = true;

    @ConfigEntry
    public boolean showIDandMetadata = false;

    @ConfigEntry
    public boolean showBlockHarvestability = true;

    @ConfigEntry(
            lowerBounds = 0, upperBounds = 5,
            configComment = "0 - top left, 1 - top, 2 - top right, 3 - bottom left, 4 - bottom, 5 - bottom right"
    )
    public byte location = 0;

    @ConfigEntry
    public boolean gradientColor = false;

    @ConfigEntry
    public int startPlaqueGradient = -1073741824;

    @ConfigEntry
    public int endPlaqueGradient = -1073741824;

    @ConfigEntry
    public int staticPlaqueColor = -1073741824;

    @ConfigEntry(lowerBounds = 0, upperBounds = 255)
    public short offset_x = 5;

    @ConfigEntry(lowerBounds = 0, upperBounds = 255)
    public short offset_y = 5;

}

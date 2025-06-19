package net.tracystacktrace.epsilon;

public enum EnumColorType {
    ARGB,
    RGB,
    GRAYSCALE;

    public int[] unpack(int color) {
        return switch (this) {
            case ARGB -> new int[]{(color >> 24) & 0xFF, (color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF};
            case RGB -> new int[]{(color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF};
            case GRAYSCALE -> new int[]{color & 0xFF};
        };
    }

    public int pack(int[] colors) {
        return switch (this) {
            case ARGB -> (((colors[0] << 24) & 0xFF) | ((colors[1] << 16) & 0xFF) | ((colors[2] << 8) & 0xFF) | (colors[3] & 0xFF));
            case RGB -> (((colors[0] << 16) & 0xFF) | ((colors[1] << 8) & 0xFF) | (colors[2] & 0xFF));
            case GRAYSCALE -> (colors[0] & 0xFF);
        };
    }
}

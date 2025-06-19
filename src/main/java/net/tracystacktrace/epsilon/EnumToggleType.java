package net.tracystacktrace.epsilon;

public enum EnumToggleType {
    ENABLE_DISABLE,
    YES_NO,
    ON_OFF;

    public String getTranslationKey(boolean b) {
        return switch (this) {
            case ENABLE_DISABLE -> b ? "epsilon.toggle.enable" : "epsilon.toggle.disable";
            case YES_NO -> b ? "epsilon.toggle.yes" : "epsilon.toggle.no";
            case ON_OFF -> b ? "epsilon.toggle.on" : "epsilon.toggle.off";
        };
    }
}

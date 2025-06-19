package net.tracystacktrace.epsilon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Advanced decoration based API for creating automated config GUIs
 * <br>
 * Recommended to be used with FoxLoader's in-built config system
 * <br>
 * This does not provide any I/O or save options, it relies on external APIs
 */
public final class Epsilon {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface StringElement {
        EnumStringType type() default EnumStringType.DEFAULT;

        String title();

        int maxLength() default 128;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ToggleElement {
        EnumToggleType type() default EnumToggleType.ENABLE_DISABLE;

        String title();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ColorElement {
        EnumColorType type() default EnumColorType.ARGB;

        String title();

        boolean advancedInterface() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ChoiceElement {
        String title();

        String[] options();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface SliderElement {
        EnumSliderType type() default EnumSliderType.STANDARD;

        String title();
    }
}

package net.tracystacktrace.epsilon;

public enum EnumStringType {
    DEFAULT,
    NUMERICAL,
    ALPHABETIC,
    ALPHANUMERICAL,
    HEX;

    public boolean valid(char c) {
        return switch (this) {
            case DEFAULT -> true;
            case NUMERICAL -> Character.isDigit(c);
            case ALPHABETIC -> Character.isAlphabetic(c);
            case ALPHANUMERICAL -> Character.isDigit(c) || Character.isAlphabetic(c);
            case HEX -> Character.isDigit(c) ||
                    c == 'A' || c == 'a' ||
                    c == 'B' || c == 'b' ||
                    c == 'C' || c == 'c' ||
                    c == 'D' || c == 'd' ||
                    c == 'E' || c == 'e' ||
                    c == 'F' || c == 'f';
        };
    }

    public String filter(String input) {
        if (input == null) return null;

        final StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (this.valid(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}

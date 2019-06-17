
public class Binary {
    private final String input;
    private Integer decimal;

    public Binary(String input) {
        this.input = input;
    }

    public int getDecimal() {
        if (decimal == null) {
            decimal = binaryToBaseTen();
        }
        return decimal;
    }

    private int binaryToBaseTen() {
        if(input.matches("^[01]*$")) {
            return bitValue(0, input);
        } else {
            return 0;
        }
    }

    private int bitValue(int current, String remaining) {
        if(remaining.length() == 0) {
            return current;
        }
        return bitValue(current * 2 + (remaining.charAt(0) - '0'), remaining.substring(1));

    }
}

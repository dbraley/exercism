public class RomanNumeral {
    private String romanNumeral;

    public RomanNumeral(int num) {
        romanNumeral = getVal(num);
    }

    public String getRomanNumeral() {
        return romanNumeral;
    }

    private static String getVal(int n) {
        if(n >= 1000) return "M" + getVal(n - 1000);
        if(n >= 900) return "CM" + getVal(n - 900);
        if(n >= 500) return "D" + getVal(n - 500);
        if(n >= 400) return "CD" + getVal(n - 400);
        if(n >= 100) return "C" + getVal(n - 100);
        if(n >= 90) return "XC" + getVal(n - 90);
        if(n >= 50) return "L" + getVal(n - 50);
        if(n >= 40) return "XL" + getVal(n - 40);
        if(n >= 10) return "X" + getVal(n - 10);
        if(n >= 9) return "IX" + getVal(n - 9);
        if(n >= 5) return "V" + getVal(n - 5);
        if(n >= 4) return "IV" + getVal(n - 4);
        if(n >= 1) return "I" + getVal(n - 1);
        else return "";
    }
}

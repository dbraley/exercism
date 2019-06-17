public class Hamming {
    public static int compute(String a, String a1) {
        int count = 0;
        if (a.length() != a1.length()) throw new IllegalArgumentException("strands not of equal length");
        for (int i = 0; i < a.length() && i < a1.length(); i++) {
            if (a.charAt(i) != a1.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}

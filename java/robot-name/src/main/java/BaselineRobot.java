import static java.util.Collections.nCopies;

public class BaselineRobot implements iRobot {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String A = String.join("", nCopies(100, ALPHABET));
    private static final String DIGITS = "0123456789";
    private static final String D = String.join("", nCopies(100, DIGITS));
    private final int a;
    private final int d;

    public BaselineRobot(int a, int d) {
        this.a = a;
        this.d = d;
    }

    @Override
    public String getName() {
        return A.substring(0, a) + D.substring(0, d);
    }

    @Override
    public void reset() {
    }
}

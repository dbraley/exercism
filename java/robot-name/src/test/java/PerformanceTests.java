import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerformanceTests {
    private static final int LOOPS = 100000;
    private static final Random reusableRng = new Random();
    private static final Collection<Result> results = new ArrayList<>();

    @BeforeClass
    public static void warmUp() {
        test(BaselineRobot::new, "");
        test(BaselineRobot::new, "");
        test(BaselineRobot::new, "");
    }

//    @Test
    public void AboRobot() {
        results.addAll(test(AboRobot::new, "ABO"));
    }

    @Test
    public void EnumRobot() {
        results.addAll(test((a,b) -> new EnumRobot(a,b,reusableRng), "REV2"));
    }

    @Test
    public void EnumRobotCachedConfig() {
        results.addAll(test((a,b) -> new CachedConfigEnumRobot(a,b,reusableRng), "REV2-CC"));
    }

    @Test
    public void MultiDigitConfigEnumRobot() {
        results.addAll(test((a,b) -> new Robot(a,b,reusableRng), "REV3"));
    }

    private static List<Result> test(BiFunction<Integer, Integer, iRobot> producer, String test) {
        return Arrays.asList(
                test(producer, test, 2, 3),
                test(producer, test, 10, 10),
                test(producer, test, 20, 30),
                test(producer, test, 97, 97),
                test(producer, test, 90, 0),
                test(producer, test, 0, 90),
                test(producer, test, 999, 999)
        );
    }

    private static Result test(BiFunction<Integer, Integer, iRobot> robotGenerator, String test, Integer alphas, Integer digits) {
        long start = System.nanoTime();
        String example = IntStream.range(0, LOOPS).mapToObj(i -> robotGenerator.apply(alphas, digits).getName())
                .reduce((a, b) -> b).orElse("");
        return new Result(alphas, digits, (System.nanoTime() - start)/LOOPS, example, test);
    }



    private static class Result {
        private final long alphas, digits, nTime;
        private final String example, test;

        private Result(long alphas, long digits, long nTime, String example, String test) {
            this.alphas = alphas;
            this.digits = digits;
            this.nTime = nTime;
            this.example = example;
            this.test = test;
        }

        public String toString() {
            return String.format("%6s - Average nTime for a{%02d}d{%02d} - %6d - ex: %.20s", test, alphas, digits, nTime, example);
        }
        public String key() {
            return String.format("a{%3d}d{%3d}", alphas, digits);
        }
    }
    @AfterClass
    public static void printResults() {
        results.stream().collect(Collectors.groupingBy(Result::key))
                .forEach((key, value) -> {
                    System.out.println("nTime for " + key + ": " + value.stream().sorted((r1, r2) -> (int)(r1.nTime - r2.nTime))
                            .map(result -> String.format("%7s{%5d}", result.test, result.nTime)).collect(Collectors.joining(", ")));
                });
    }
}

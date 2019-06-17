
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Ignore
public class PerformanceTestsExperimental {

    private static final int LOOPS = 1000000;
    private static final Random reusableRng = new Random();
    private static final Collection<Result> results = new ArrayList<>();

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String A = String.join("", nCopies(10, ALPHABET));
    private static final String DIGITS = "0123456789";
    private static final String D = String.join("", nCopies(10, DIGITS));

    private Map<String, List<Function<Random, String>>> functions = new HashMap<>();
    private Map<String, List<EnumRobot.CharClass>> configs = new HashMap<>();
    private Map<String, EnumRobot> robots = new HashMap<>();

    @Before
    public void warmUp() {
        test((a,d) -> "nil", "nil");
        test((a,d) -> "nil" + reusableRng.nextLong(), "nil");
        test((a,d) -> "nil" + A.substring(0, a) + D.substring(0, d), "nil");
    }

    @Test
    public void baseline() {
        results.addAll(test((a,d) -> A.substring(0, a) + D.substring(0, d), "B"));
    }

    @Test
    public void solE() {
//        results.addAll(test((a,d) -> new EnumRobot(getConfig(a, d), reusableRng).createNewName(), "E"));
    }


    private List<EnumRobot.CharClass> getConfig(int a, int d) {
        return configs.computeIfAbsent("a{" + a + "}d{" + d + "}", key -> Stream.of(
                nCopies(a, EnumRobot.CharClass.ALPHA),
                nCopies(d, EnumRobot.CharClass.DIGIT)).flatMap(Collection::stream).collect(toList()));
    }

    private List<Function<Random, String>> getFunctionsCaching(int a, int d) {
        Function<Random, String> alpha = r -> "" + (char) ('A' + r.nextInt(26));
        int maxLongDigits = 19;
        return functions.computeIfAbsent("a{" + a + "}d{" + d + "}", key -> Stream.of(nCopies(a, alpha),
                nCopies(d / maxLongDigits, getRandomStringFunction(maxLongDigits)), nCopies(1, getRandomStringFunction(d % maxLongDigits)))
                .flatMap(col -> col.stream()).collect(toList()));
    }

    private Function<Random, String> getRandomStringFunction(int size) {
//        return size == 0 ? r -> "" : r -> String.format("%0" + (size+1) + "d", Math.abs(r.nextLong())).substring(1, size+1);
        String format = "%" + size + "." + size + "s";
        return size == 0 ? r -> "" : r -> String.format(format, Long.toUnsignedString(r.nextLong())).replace(' ', '0');
    }


    private List<Result> test(BiFunction<Integer, Integer, String> producer, String test) {
        return Arrays.asList(
                test(producer, test, 2, 3),
                test(producer, test, 10, 10),
                test(producer, test, 20, 30),
                test(producer, test, 97, 97),
                test(producer, test, 90, 0),
                test(producer, test, 0, 90)
                );
    }

    private Result test(BiFunction<Integer, Integer, String> producer, String test, Integer alphas, Integer digits) {
        long start = System.nanoTime();
        String example = IntStream.range(0, LOOPS).mapToObj(i -> producer.apply(alphas, digits)).reduce((a, b) -> b).orElse("");
        return new Result(alphas, digits, (System.nanoTime() - start)/LOOPS, example, test);
    }

    private static String randomCharString(int howMany, char lowerBound, char upperBound) {
        return mkString(randomCharStream(howMany, lowerBound, upperBound));
    }

    private static String randomCharString2(int howMany, char lowerBound, char upperBound) {
        return mkString(randomCharStream2(howMany, lowerBound, upperBound));
    }

    private static Stream<Character> randomCharStream(int howMany, char lowerBound, char upperBound) {
        return new Random().ints(lowerBound, upperBound + 1)
                .mapToObj(i -> (char)i)
                .limit(howMany);
    }
    private static Stream<Character> randomCharStream2(int howMany, char lowerBound, char upperBound) {
        return reusableRng.ints(lowerBound, upperBound + 1)
                .mapToObj(i -> (char)i)
                .limit(howMany);
    }

    private static Stream<String> randomStringStream(int howMany, char lowerBound, char upperBound) {
        return new Random().ints(lowerBound, upperBound + 1)
                .mapToObj(i -> ""+(char)i)
                .limit(howMany);
    }

    private static Stream<String> randomStringStream2(int howMany, char lowerBound, char upperBound) {
        return reusableRng.ints(lowerBound, upperBound + 1)
                .mapToObj(i -> ""+(char)i)
                .limit(howMany);
    }

    private static Stream<String> randomStringStream3(int howMany, char lowerBound, char upperBound) {
        return reusableRng.ints(howMany, lowerBound, upperBound + 1)
                .mapToObj(i -> ""+(char)i);
    }

    private static <A> String mkString(Stream<A> as) {
        return as.map(Object::toString)
                .collect(joining(""));
    }

    private class Result {
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
            return String.format("%s - Average nTime for a{%02d}d{%02d} - %6d - ex: %s", test, alphas, digits, nTime, example);
        }
    }
    @AfterClass
    public static void printResults() {
        Comparator<Result> sort = (r1, r2) -> {
            if (r1.alphas == r2.alphas && r1.digits == r2.digits) {
                return (int) (r1.nTime - r2.nTime);
            } else {
                return (int) (r1.alphas - r2.alphas);
            }
        };
        results.stream().sorted(sort).forEach(System.out::println);
    }

    private static class SolutionD {
        private final int a;
        private final int d;

        public SolutionD(int a, int d) {
            this.a = a;
            this.d = d;
        }

        public String get() {
            return Stream.of(randomStringStream3(a, 'A', 'Z'),
                    randomStringStream3(d, '0', '9')).flatMap(c->c).collect(joining());
        }
    }
}

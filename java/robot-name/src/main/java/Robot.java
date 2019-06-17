import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;

public class Robot implements iRobot {
    private static Map<String, List<Sequence>> cachedConfigs = new ConcurrentHashMap<>();

    public enum CharClass {
        ALPHA((rng, size) -> rng.ints(size, 'A', 'Z' + 1).mapToObj(i -> ""+(char)i)),
        DIGIT((rng, size) -> rng.ints(size, '0', '9' + 1).mapToObj(i -> ""+(char)i)),
        HEX((rng, size) -> rng.ints(size, 0, 17).mapToObj(i -> Integer.toHexString(i).toUpperCase())),
        LOWER((rng, size) -> rng.ints(size, 'a', 'z' + 1).mapToObj(i -> ""+(char)i)),
        DASH((rng, size) -> Collections.nCopies(size, "-").stream());

        private final BiFunction<Random, Integer, Stream<String>> b;
        CharClass(BiFunction<Random, Integer, Stream<String>> b) {
            this.b = b;
        }

        public Stream<String> getStream(Random rng, int size) {
            return b.apply(rng, size);
        }

    }

    private final List<Sequence> config;
    private final Set<String> usedNames = new HashSet<>();
    private String name;

    public Robot() {
        this(2,3, new Random());
    }

    public Robot(int a, int d, Random rng) {
        this(getADConfigFactoryLike(a,d,rng));
    }

    public Robot(List<Sequence> config) {
        this.config = config;
        this.name = createUniqueNewName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {
        usedNames.add(name);
        name = createUniqueNewName();
    }

    private String createUniqueNewName() {
        String candidate = createNewName();
        while(usedNames.contains(candidate)) {
            candidate = createNewName();
        }
        return candidate;
    }

    public String createNewName() {
        return config.stream().map(Sequence::getStream).flatMap(identity()).collect(joining());
    }

    private static List<Sequence> getADConfigFactoryLike(int a, int d, Random rng) {
        return cachedConfigs.computeIfAbsent("a{" + a + "}d{" + d + "}", k -> Arrays.asList(
                new Sequence(Robot.CharClass.ALPHA, a, rng),
                new Sequence(Robot.CharClass.DIGIT, d, rng)));
    }

    public static class Sequence {

        private final Random rng;
        private final int size;
        private final CharClass seqGen;

        public Sequence(CharClass seqGen, int size, Random rng) {
            this.size = size;
            this.rng = rng;
            this.seqGen = seqGen;
        }

        public Stream<String> getStream() {
            return seqGen.getStream(rng, size);
        }
    }
}

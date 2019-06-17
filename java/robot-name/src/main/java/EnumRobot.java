import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.joining;

public class EnumRobot implements iRobot {
    private final List<CharClass> config;
    private final Random rng;
    private final Set<String> usedNames = new HashSet<>();
    private String name;

    public enum CharClass {
        ALPHA(f -> "" + (char) ('A' + f.nextInt(26))),
        DIGIT(f -> "" + f.nextInt(10)),
        HEX(f -> Integer.toHexString(f.nextInt(16))),
        LOWER(f -> "" + (char) ('a' + f.nextInt(26))),
        DASH(f -> "-");

        private final Function<Random, String> f;

        CharClass(Function<Random, String> f) {
            this.f = f;
        }

        public String getValue(Random rng) {
            return f.apply(rng);
        }
    }

    public EnumRobot() {
        this(Arrays.asList(CharClass.ALPHA, CharClass.ALPHA, CharClass.DIGIT, CharClass.DIGIT, CharClass.DIGIT), new Random());
    }

    public EnumRobot(Integer a, Integer d, Random rng) {
        this(Stream.of(nCopies(a, CharClass.ALPHA), nCopies(d, CharClass.DIGIT))
                .flatMap(Collection::stream).collect(Collectors.toList()), rng);
    }

    public EnumRobot(List<CharClass> config, Random rng) {
        this.config = config;
        this.rng = rng;
        this.name = createUniqueNewName();
    }

    public String getName() {
        return name;
    }

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

    private String createNewName() {
        return config.stream().map(cc -> cc.getValue(rng)).collect(joining());
    }
}

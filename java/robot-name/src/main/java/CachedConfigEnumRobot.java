import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.nCopies;

public class CachedConfigEnumRobot extends EnumRobot {
    private static Map<String, List<CharClass>> cachedConfigs = new ConcurrentHashMap<>();
    public CachedConfigEnumRobot(Integer a, Integer b, Random reusableRng) {
        super(getConfig(a, b), reusableRng);
    }

    private static List<CharClass> getConfig(int a, int d) {
        return cachedConfigs.computeIfAbsent("a{" + a + "}d{" + d + "}", k -> Stream.of(nCopies(a, CharClass.ALPHA), nCopies(d, CharClass.DIGIT))
                .flatMap(Collection::stream).collect(Collectors.toList()));

    }
}

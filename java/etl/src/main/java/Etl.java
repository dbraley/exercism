import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Etl {
    public Map<String, Integer> transform(Map<Integer, List<String>> old) {
        return old.entrySet().stream().flatMap(entry -> expandEntryStream(entry)).collect(toMap());
//        return old.entrySet().stream().collect(HashMap::new,
//                (m, e) -> e.getValue().stream().forEach(s -> m.put(s.toLowerCase(), e.getKey())),
//                HashMap::putAll);
    }

    private Collector<AbstractMap.SimpleImmutableEntry<String, Integer>, ?, Map<String, Integer>> toMap() {
        return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
    }

    private Stream<AbstractMap.SimpleImmutableEntry<String, Integer>> expandEntryStream(Map.Entry<Integer, List<String>> entry) {
        return entry.getValue().stream().map(letter -> toLowerCaseValuePair(letter, entry.getKey()));
    }

    private AbstractMap.SimpleImmutableEntry<String, Integer> toLowerCaseValuePair(String letter, Integer value) {
        return new AbstractMap.SimpleImmutableEntry<>(letter.toLowerCase(), value);
    }
}

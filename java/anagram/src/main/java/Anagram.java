import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Anagram {

    private final String text;
    private Map<Character, Long> characterLongMap;

    public Anagram(String diaper) {
        text = diaper;
    }

    public List<String> match(List<String> strings) {
        return strings.stream()
                .filter(s-> !text.equalsIgnoreCase(s))
                .filter(s-> getCharCountsForInput().equals(collectCharCounts(s)))
                .collect(toList());
    }

    private Map<Character, Long> getCharCountsForInput() {
        if (characterLongMap == null) {
            characterLongMap = collectCharCounts(this.text);
        }
        return characterLongMap;
    }

    private Map<Character, Long> collectCharCounts(String text) {
        return text.toLowerCase().chars().mapToObj(i -> (char) i).collect(groupingBy(c -> c, counting()));
    }
}

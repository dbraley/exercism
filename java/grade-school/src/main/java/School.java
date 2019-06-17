import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class School {

    private Map<Integer, Collection<String>> db = new ConcurrentHashMap<>();

    public Map<Integer, Collection<String>> db() {
        return new HashMap<>(db);
    }

    public void add(String name, int grade) {
        db.computeIfAbsent(grade, g -> new ConcurrentLinkedQueue<>()).add(name);
    }

    public Collection<String> grade(int grade) {
        return db.computeIfAbsent(grade, g -> new ConcurrentLinkedQueue<>());
    }

    public Map<Integer, List<String>> sort() {
        return db.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(),
                        entry.getValue().stream().sorted().collect(toList())))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

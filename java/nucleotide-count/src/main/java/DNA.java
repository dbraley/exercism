import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class DNA {
    private static final List<Character> NUCLEOTIDES = Collections.unmodifiableList(Arrays.asList('A', 'C', 'G', 'T'));
    private final String strand;
    private Map<Character, Integer> countMap;

    public DNA(String strand) {
        this.strand = strand;
    }

    public int count(char nucleotide) {
        if(NUCLEOTIDES.contains(nucleotide)) {
            return nucleotideCounts().get(nucleotide);
        } else {
            throw new IllegalArgumentException("Can't count invalid nucleotide " + nucleotide);
        }
    }

    public Map<Character, Integer> nucleotideCounts() {
        if(countMap == null) {
            countMap = addDummyValues(nucleotideCountsLong(doParallel()));
        }
        return countMap;
    }

    private boolean doParallel() {
        return strand.length() > 25;
    }

    private Map<Character, Integer> nucleotideCountsLong(boolean doParallel) {
        if (doParallel) {
            return nucleotideCountsLongParallel();
        } else {
            return nucleotideCountsLongSerial();
        }
    }

    private Map<Character, Integer> nucleotideCountsLongSerial() {
        return strand.chars().mapToObj(i -> (char) i)
                .collect(groupingBy(c -> c, collectingAndThen(counting(), Long::intValue)));
    }

    private Map<Character, Integer> nucleotideCountsLongParallel() {
        return strand.chars().parallel().mapToObj(i -> (char) i)
                .collect(groupingByConcurrent(c -> c, collectingAndThen(counting(), Long::intValue)));
    }

    private Map<Character, Integer> addDummyValues(Map<Character, Integer> characterIntegerMap) {
        NUCLEOTIDES.forEach(c -> characterIntegerMap.putIfAbsent(c, 0));
        return characterIntegerMap;
    }

}


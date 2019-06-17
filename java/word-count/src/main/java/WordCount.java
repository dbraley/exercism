import java.util.Map;
import java.util.regex.Pattern;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class WordCount {

    private static final String WORD_BOUNDARY = "[\\s!&@\\$\\^%:,]+";

    public Map<String, Integer> phrase(String words) {
        return Pattern.compile(WORD_BOUNDARY).splitAsStream(words)
                .map(String::toLowerCase).collect(groupingBy(identity(), collectingAndThen(counting(), Long::intValue)));
    }
}

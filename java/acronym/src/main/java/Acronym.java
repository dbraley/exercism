import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class Acronym {
    private static final String WORD_BOUNDARY = "[\\s\\-]+|[a-z](?=[A-Z])";

    public static String generate(String words) {
        return Pattern.compile(WORD_BOUNDARY).splitAsStream(words)
                .map(Acronym::getFirstLetterCapitalized)
                .collect(joining());
    }

    private static String getFirstLetterCapitalized(String word) {
        return word.substring(0,1).toUpperCase();
    }
}

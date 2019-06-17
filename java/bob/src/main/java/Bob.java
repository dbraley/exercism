import java.text.Normalizer;
import java.util.regex.Pattern;

public class Bob {

    private static final String EMPTY_RESPONSE = "Fine. Be that way!";
    private static final String YELL_RESPONSE = "Whoa, chill out!";
    private static final String QUESTION_RESPONSE = "Sure.";
    private static final String NORMAL_RESPONSE = "Whatever.";

    public String hey(String s) {
        if (isEmpty(s)) {
            return EMPTY_RESPONSE;
        } else if (isYelling(s)) {
            return YELL_RESPONSE;
        } else if (isQuestion(s)){
            return QUESTION_RESPONSE;
        }
        return NORMAL_RESPONSE;
    }

    private boolean isEmpty(String s) {
        return s.matches("\\s*");
    }

    private boolean isYelling(String s) {
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        return Pattern.compile("[A-Z]").matcher(normalized).find() && !Pattern.compile("[a-z]").matcher(normalized).find();
    }

    private boolean isQuestion(String s) {
        return s.endsWith("?");
    }
}

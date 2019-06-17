import java.text.Normalizer;
import java.util.stream.IntStream;

public class Pangrams {
    public static boolean isPangram(String input) {
        final String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD).toLowerCase();
        return IntStream.range('a', 'z').mapToObj(i -> String.valueOf((char)i)).allMatch(s -> normalizedInput.contains(s));
    }
}

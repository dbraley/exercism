import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhoneNumber {
    private static final String SPACER = oneOf(esc('s'), esc('-'), esc('.'));
    private static Pattern PRETTY = Pattern.compile(
            optional("1") +
            optional(esc('(')) +
            optional(group("areaCode", digits(3))) +
            optional(esc(')')) +
            optional(SPACER) +
            group("first3", digits(3)) +
            optional(SPACER) +
            group("last4", digits(4)));

    private static String digits(int num) {
        return "\\d{" + num + "}";
    }

    private static String oneOf(String... regexs) {
        return "[" + Arrays.stream(regexs).collect(Collectors.joining()) + "]";
    }

    private static String esc(char c) {
        return "\\" + c;
    }

    private static String optional(String regex) {
        return regex + "?";
    }

    private static String group(String groupName, String regex) {
        return "(?<" + groupName + ">" + regex + ")";
    }

    private final String areaCode;
    private final String first3;
    private final String last4;

    public PhoneNumber(String numberString) {
        Matcher matcher = PRETTY.matcher(numberString);
        if (matcher.matches()) {
            areaCode = matcher.group("areaCode");
            first3 = matcher.group("first3");
            last4 = matcher.group("last4");
        } else {
            areaCode = "000";
            first3 = "000";
            last4 = "0000";

        }
    }

    public String getNumber() {
        return (areaCode!=null? areaCode : "") + first3 + last4;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String pretty() {
        if (areaCode != null) {
            return String.format("(%s) %s-%s", areaCode, first3, last4);
        } else {
            return String.format("%s-%s", first3, last4);
        }
    }
}

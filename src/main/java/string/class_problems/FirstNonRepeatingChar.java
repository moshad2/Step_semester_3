package string.class_problems;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        Map<Character, Integer> counts = new LinkedHashMap<>();

        for (char ch : text.toCharArray()) {
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        }

        for (char ch : text.toCharArray()) {
            if (counts.get(ch) == 1) {
                return ch;
            }
        }
        return '\0'; // Sentinel value for no unique character found
    }

    public static void testString(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result == '\0') {
            System.out.println("Input: \"" + text + "\" -> No Non-Repeating Character Found");
        } else {
            System.out.println("Input: \"" + text + "\" -> First Non-Repeating Character: '" + result + "'");
        }
    }

    public static void main(String[] args) {
        testString("swiss");
        testString("aabbcc");
    }
}

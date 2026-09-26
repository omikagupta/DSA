import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // 1. Store key-value pairs in a HashMap for O(1) lookup
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder();
        boolean insideBracket = false;

        // 2. Parse the string character by character
        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBracket = true;
            } else if (c == ')') {
                insideBracket = false;
                String key = keyBuilder.toString();
                // Replace key with value if present, otherwise with "?"
                result.append(map.getOrDefault(key, "?"));
                keyBuilder.setLength(0); // Clear key buffer
            } else {
                if (insideBracket) {
                    keyBuilder.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
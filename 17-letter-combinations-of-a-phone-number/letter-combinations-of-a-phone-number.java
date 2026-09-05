import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> ans = new ArrayList<>();

        if (digits.length() == 0) {
            return ans;
        }

        Map<Character, String> map = new HashMap<>();

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        StringBuilder path = new StringBuilder();

        recurse(digits, 0, path, ans, map);

        return ans;
    }

    public void recurse(
    String digits,
    int index,
    StringBuilder path,
    List<String> ans,
    Map<Character, String> map
) {

    if (index == digits.length()) {
        ans.add(path.toString());
        return;
    }

    String letters = map.get(digits.charAt(index));

    for (int i = 0; i < letters.length(); i++) {

        char ch = letters.charAt(i);

        
        path.append(ch);

     
        recurse(digits, index + 1, path, ans, map);

        
        path.deleteCharAt(path.length() - 1);
    }
}
}
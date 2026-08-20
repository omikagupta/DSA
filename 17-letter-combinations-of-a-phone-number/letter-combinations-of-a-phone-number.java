class Solution {
    public List<String> letterCombinations(String digits) {

        List<String> ans = new ArrayList<>();

        if (digits.length() == 0) {
            return ans;
        }

        StringBuilder path = new StringBuilder();

        String[] map = {
            "", "", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
        };

        backtrack(ans, path, digits, 0, map);

        return ans;
    }

    public void backtrack(List<String> ans,
                          StringBuilder path,
                          String digits,
                          int i,
                          String[] map) {

   
        if (i == digits.length()) {
            ans.add(path.toString());
            return;
        }
        String letters = map[digits.charAt(i) - '0'];
        for (int j = 0; j < letters.length(); j++) {

         
            path.append(letters.charAt(j));

          
            backtrack(ans, path, digits, i + 1, map);

        
            path.deleteCharAt(path.length() - 1);
        }
    }
}
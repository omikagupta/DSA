import java.util.*;

class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();

        backtrack(s, ans, path, 0);

        return ans;
    }

    boolean palindrome(String s, int left, int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    void backtrack(
        String s,
        List<List<String>> ans,
        List<String> path,
        int start
    ) {

        if (start == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {

            if (palindrome(s, start, end)) {

                path.add(s.substring(start, end + 1));

                backtrack(s, ans, path, end + 1);

                path.remove(path.size() - 1);
            }
        }
    }
}
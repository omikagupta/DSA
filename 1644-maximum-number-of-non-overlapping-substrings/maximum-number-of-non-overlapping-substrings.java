import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) first[ch] = i;
            last[ch] = i;
        }

        // Collect all valid expanded ranges [left, right]
        List<int[]> ranges = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;
            
            int left = first[c];
            int right = last[c];
            boolean valid = true;

            for (int i = left; i <= right; i++) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < left) {
                    valid = false; // Cannot start at left because a character extends further left
                    break;
                }
                right = Math.max(right, last[ch]);
            }

            if (valid) {
                ranges.add(new int[]{left, right});
            }
        }

        // Sort ranges by their end position (right boundary)
        ranges.sort((a, b) -> Integer.compare(a[1], b[1]));

        // Greedy interval selection
        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] range : ranges) {
            int left = range[0];
            int right = range[1];
            if (left > lastEnd) {
                result.add(s.substring(left, right + 1));
                lastEnd = right;
            }
        }

        return result;
    }
}
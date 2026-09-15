class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; // End index of the last selected palindrome

        // Expand around center for each possible center
        for (int i = 0; i < n; i++) {
            // Check odd-length center (i, i) and even-length center (i, i + 1)
            for (int[] center : new int[][]{{i, i}, {i, i + 1}}) {
                int left = center[0];
                int right = center[1];

                // Expand outward as long as it forms a valid palindrome
                while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                    int len = right - left + 1;

                    // Valid palindrome found of length at least k
                    if (len >= k) {
                        // Ensure it doesn't overlap with the previously selected palindrome
                        if (left > lastEnd) {
                            count++;
                            lastEnd = right; // Update last selected end position
                        }
                        break; // Stop expanding further from this center
                    }

                    left--;
                    right++;
                }
            }
        }

        return count;
    }
}
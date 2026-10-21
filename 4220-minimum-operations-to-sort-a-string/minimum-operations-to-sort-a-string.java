
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        char mn = s.charAt(0);
        char mx = s.charAt(0);
        boolean isSorted = true;

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);

            mn = (char) Math.min(mn, c);
            mx = (char) Math.max(mx, c);

            if (s.charAt(i) < s.charAt(i - 1)) {
                isSorted = false;
            }
        }

        if (isSorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);

            if (c == mn || c == mx) {
                return 2;
            }
        }

       
        return 3;
    }
}

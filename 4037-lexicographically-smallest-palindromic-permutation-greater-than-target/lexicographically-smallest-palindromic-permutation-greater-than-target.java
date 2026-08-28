import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
            halfCount[i] = count[i] / 2;
        }

        // A palindrome cannot be formed
        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int m = n / 2;
        String best = null;

        // Case 1: First half shares a prefix of length i with target[0..i-1],
        // and at index i, firstHalf[i] > target[i].
        for (int i = m - 1; i >= 0; i--) {
            int[] curHalf = halfCount.clone();
            boolean prefixValid = true;
            char[] prefix = new char[m];

            // Build matching prefix target[0 .. i-1]
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (curHalf[c] > 0) {
                    curHalf[c]--;
                    prefix[j] = target.charAt(j);
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            // Try the smallest char strictly greater than target.charAt(i)
            int targetCharVal = target.charAt(i) - 'a';
            for (int c = targetCharVal + 1; c < 26; c++) {
                if (curHalf[c] > 0) {
                    prefix[i] = (char) ('a' + c);
                    curHalf[c]--;

                    // Fill the remainder greedily with smallest available characters
                    int fillIdx = i + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (curHalf[ch] > 0) {
                            prefix[fillIdx++] = (char) ('a' + ch);
                            curHalf[ch]--;
                        }
                    }

                    String candidate = buildFullPalindrome(prefix, midChar, n);
                    if (candidate.compareTo(target) > 0) {
                        if (best == null || candidate.compareTo(best) < 0) {
                            best = candidate;
                        }
                    }
                    break; // Found the minimal valid character for this prefix length
                }
            }
        }

        // Case 2: First half exactly matches target[0 .. m-1]
        int[] curHalf = halfCount.clone();
        char[] prefix = new char[m];
        boolean canMatchFirstHalf = true;

        for (int j = 0; j < m; j++) {
            int c = target.charAt(j) - 'a';
            if (curHalf[c] > 0) {
                curHalf[c]--;
                prefix[j] = target.charAt(j);
            } else {
                canMatchFirstHalf = false;
                break;
            }
        }

        if (canMatchFirstHalf) {
            String candidate = buildFullPalindrome(prefix, midChar, n);
            if (candidate.compareTo(target) > 0) {
                if (best == null || candidate.compareTo(best) < 0) {
                    best = candidate;
                }
            }
        }

        return best == null ? "" : best;
    }

    private String buildFullPalindrome(char[] firstHalf, char midChar, int n) {
        char[] res = new char[n];
        int m = firstHalf.length;

        for (int i = 0; i < m; i++) {
            res[i] = firstHalf[i];
            res[n - 1 - i] = firstHalf[i];
        }

        if (n % 2 != 0) {
            res[m] = midChar;
        }

        return new String(res);
    }
}
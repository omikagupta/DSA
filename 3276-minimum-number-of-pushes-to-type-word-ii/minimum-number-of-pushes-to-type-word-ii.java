class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int pushes = 0;
        int cost = 1;

        // Traverse from highest frequency to lowest
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;

            pushes += freq[i] * cost;

            // Every 8 letters, increase push count
            if ((25 - i + 1) % 8 == 0) {
                cost++;
            }
        }

        return pushes;
    }
}
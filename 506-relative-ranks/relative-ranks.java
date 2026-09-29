class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] ans = new String[n];

        int[][] pair = new int[n][2];

        for (int i = 0; i < n; i++) {
            pair[i][0] = score[i];
            pair[i][1] = i;
        }
        Arrays.sort(pair, (a, b) -> b[0] - a[0]);

        for (int rank = 0; rank < n; rank++) {
            int index = pair[rank][1];

            if (rank == 0) {
                ans[index] = "Gold Medal";
            } 
            else if (rank == 1) {
                ans[index] = "Silver Medal";
            } 
            else if (rank == 2) {
                ans[index] = "Bronze Medal";
            } 
            else {
                ans[index] = String.valueOf(rank + 1);
            }
        }

        return ans;
    }
}
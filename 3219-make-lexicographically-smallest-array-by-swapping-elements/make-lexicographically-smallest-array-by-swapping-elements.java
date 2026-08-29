import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store indices and sort them based on nums values
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {
            int j = i + 1;

            // Find all elements belonging to the same swappable group
            while (j < n &&
                   nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            // Get original indices of this group
            Integer[] positions = Arrays.copyOfRange(indices, i, j);

            // Sort positions so smaller values go to smaller indices
            Arrays.sort(positions);

            for (int k = i; k < j; k++) {
                ans[positions[k - i]] = nums[indices[k]];
            }

            i = j;
        }

        return ans;
    }
}
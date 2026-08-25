class Solution {
    public int missingMultiple(int[] nums, int k) {

        HashSet<Integer> set = new HashSet<>();

        // Store all elements
        for (int num : nums) {
            set.add(num);
        }

        // Check k, 2k, 3k, ...
        int multiple = k;

        while (set.contains(multiple)) {
            multiple += k;
        }

        return multiple;
    }
}
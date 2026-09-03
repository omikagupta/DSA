class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            } else {
                minEven = Math.min(minEven, x);
            }
        }

        // If all numbers are already of the same parity, it's always possible
        if (minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }

        // To make all elements odd: every even number x must satisfy x - minOdd >= 1 (x > minOdd)
        // If minEven < minOdd, we cannot make all elements odd or even
        return minOdd < minEven;
    }
}
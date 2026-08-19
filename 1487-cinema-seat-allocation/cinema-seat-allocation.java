import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store reserved seats row wise
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        // Rows without any reservation can fit 2 families
        int ans = (n - map.size()) * 2;

        for (int mask : map.values()) {

            int families = 0;

            // Seats 2,3,4,5
            if ((mask & 0b0000011110) == 0) {
                families++;
            }

            // Seats 6,7,8,9
            if ((mask & 0b0111100000) == 0) {
                families++;
            }

            // Seats 4,5,6,7
            if (families == 0 && (mask & 0b0001111000) == 0) {
                families++;
            }

            ans += families;
        }

        return ans;
    }
}
import java.util.*;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        // Max Heap to store fuel of all reachable stations
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int reach = startFuel;   // Farthest distance we can currently reach
        int stops = 0;           // Number of refuels
        int i = 0;               // Current station index
        int n = stations.length;

        // Continue until we can reach the target
        while (reach < target) {

            // Add all stations that are reachable with current fuel
            while (i < n && stations[i][0] <= reach) {
                maxHeap.offer(stations[i][1]);
                i++;
            }

            // No reachable station left to refuel from
            if (maxHeap.isEmpty()) {
                return -1;
            }

            // Refuel at the station with the maximum fuel
            reach += maxHeap.poll();
            stops++;
        }

        return stops;
    }
}
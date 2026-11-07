import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        // Free servers heap: ordered by weight, then by index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Busy servers heap: ordered by release time (long), then weight, then index
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        // Initialize free servers: [weight, index]
        for (int i = 0; i < servers.length; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // If no servers are free, jump time to when the next server becomes available
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }

            // Release all servers that become free at or before currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] freed = busyServers.poll();
                // freed format: [releaseTime, weight, index]
                freeServers.offer(new int[] { (int) freed[1], (int) freed[2] });
            }

            // Assign the task to the best available server
            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            
            // Push server to busy queue: [releaseTime, weight, index]
            busyServers.offer(new long[] { currentTime + tasks[j], weight, index });
        }

        return ans;
    }
}
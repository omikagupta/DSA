import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // Sort queries by time
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));
        
        // Sort logs by time
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        
        int[] ans = new int[m];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        
        int left = 0, right = 0;
        int numLogs = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int qIndex = sortedQueries[i][1];
            int startTime = qTime - x;
            
            // Add logs that fall within or before the query time (right pointer)
            while (right < numLogs && logs[right][1] <= qTime) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }
            
            // Remove logs that fall outside the window to the left (left pointer)
            while (left < numLogs && logs[left][1] < startTime) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // Servers with zero requests = Total servers (n) - active servers
            ans[qIndex] = n - activeServers;
        }
        
        return ans;
    }
}
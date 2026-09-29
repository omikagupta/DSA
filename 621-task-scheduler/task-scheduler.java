class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Frequency table for task counts (A-Z)
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Find the maximum frequency among all tasks
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        // Count how many tasks share the maximum frequency
        int maxFreqCount = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                maxFreqCount++;
            }
        }

        // Formula: Calculate the minimum frame required by the most frequent task
        int intervals = (maxFreq - 1) * (n + 1) + maxFreqCount;

        // Total intervals required cannot be less than total number of tasks
        return Math.max(intervals, tasks.length);
    }
}
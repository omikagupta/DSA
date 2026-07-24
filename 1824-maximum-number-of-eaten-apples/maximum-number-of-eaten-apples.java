class Solution {
    public int eatenApples(int[] apples, int[] days) {

        int ans = 0;
        int day = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        while (day < apples.length || !pq.isEmpty()) {

          
            if (day < apples.length && apples[day] > 0) {
                pq.offer(new int[]{day + days[day], apples[day]});
            }

            
            while (!pq.isEmpty() && pq.peek()[0] <= day) {
                pq.poll();
            }

           
            if (!pq.isEmpty()) {
                int[] curr = pq.poll();
                curr[1]--;
                ans++;

                if (curr[1] > 0) {
                    pq.offer(curr);
                }
            }

            day++;
        }

        return ans;
    }
}
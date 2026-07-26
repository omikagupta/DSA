class Solution {
    public int eatenApples(int[] apples, int[] days) {
        int day=0;
        int ans=0;
        int expiry=0;
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        while(day<apples.length || !pq.isEmpty()){
              while(!pq.isEmpty() && pq.peek()[0] <= day){
    pq.poll();
}
               if(day<apples.length && apples[day]>0 ){
                 expiry=day+days[day];
                pq.offer(new int[]{expiry,apples[day]}); }
                if(!pq.isEmpty()){
                int [] current=pq.poll();
               current[1]--;
                ans++;
                
               if(current[1]>0) pq.offer(current);
                   } day++;
    }
       return ans;
        }

    }

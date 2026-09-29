class Solution {
    class Pair{
        int value;
        int index;
        Pair(int value,int index){
            this.value=value;
            this.index=index;
        }
    }
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] ans = new String[n];

        // int[][] pair = new int[n][2];

        // for (int i = 0; i < n; i++) {
        //     pair[i][0] = score[i];
        //     pair[i][1] = i;
        // }
        // Arrays.sort(pair, (a, b) -> b[0] - a[0]);

        // for (int rank = 0; rank < n; rank++) {
        //     int index = pair[rank][1];

        //     if (rank == 0) {
        //         ans[index] = "Gold Medal";
        //     } 
        //     else if (rank == 1) {
        //         ans[index] = "Silver Medal";
        //     } 
        //     else if (rank == 2) {
        //         ans[index] = "Bronze Medal";
        //     } 
        //     else {
        //         ans[index] = String.valueOf(rank + 1);
        //     }
        // }
        
PriorityQueue<Pair> pq =new PriorityQueue<>((a,b)->b.value-a.value);
for(int i=0;i<n;i++){
    pq.add(new Pair(score[i],i));
}

if(!pq.isEmpty()){
    ans[pq.peek().index]="Gold Medal";
    pq.poll();
}
if(!pq.isEmpty()){
    ans[pq.peek().index]="Silver Medal";
    pq.poll();
}
if(!pq.isEmpty()){
    ans[pq.peek().index]="Bronze Medal";
    pq.poll();
}
int rank=4;

while(!pq.isEmpty()){
    ans[pq.peek().index] = String.valueOf(rank);
    pq.poll();
    rank++;
}

        return ans;
    }
}
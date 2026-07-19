class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->Integer.compare(a[1],b[1]));
          int currstart=intervals[0][0];
          int currend=intervals[0][1];
          int count=0;
          for(int i=1;i<intervals.length;i++){
            int start=intervals[i][0];
            int end=intervals[i][1];
            if(start>=currend){
                currstart=start;
                currend=end;
            }
            else{
                count++;
          }
         
    }
     return count;
}
}
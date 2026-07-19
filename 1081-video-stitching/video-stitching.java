class Solution {
    public int videoStitching(int[][] clips, int time) {

     
        Arrays.sort(clips, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        int currEnd = 0;    
        int farthest = 0;  
        int i = 0;
        int n = clips.length;

        while (currEnd < time) {

          
            while (i < n && clips[i][0] <= currEnd) {
                farthest = Math.max(farthest, clips[i][1]);
                i++;
            }

            if (farthest == currEnd)
                return -1;

        
            ans++;
            currEnd = farthest;
        }

        return ans;
    }
}
class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]>q=new LinkedList<>();
        int fresh=0;
        int n=grid.length;
        int m=grid[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
if(grid[i][j]==2){
    q.offer(new int[]{i,j});
}
if(grid[i][j]==1){
    fresh++;
}

            }
        }
        if(fresh==0) return 0;
        int[][]dir={
            {-1,0}, 
                 {1,0},   
                      {0,-1}, 
                           {0,1}
                               };
                               int minutes=0;
        while(!q.isEmpty()){
            int size=q.size();
          
            for(int i=0;i<size;i++){
                int [] cell=q.poll();
                int r=cell[0];
                int c=cell[1];
                for(int[]d:dir){
                    int mr=r+d[0];
                    int mc=c+d[1];
                    if (mr >= 0 && mc >= 0 &&
    mr < grid.length && mc < grid[0].length &&
    grid[mr][mc] == 1) {

    grid[mr][mc] = 2;
    fresh--;
    q.offer(new int[]{mr, mc});
}
                }
            
            }
          if (!q.isEmpty()) {
                minutes++;
            } 
        }
        if(fresh>0){
            return -1;
        }
        else return minutes;
    }
}
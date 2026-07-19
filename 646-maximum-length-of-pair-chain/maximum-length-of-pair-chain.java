class Solution {
    public int findLongestChain(int[][] pairs) {
Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
int count=1;
int currstart=pairs[0][0];
int currend=pairs[0][1];
for(int i=1;i<pairs.length;i++){
    int start=pairs[i][0];
    int end=pairs[i][1];
    if(start>currend){
        count++;
            currstart = start;
      currend = end;
    }

}
return count;



    }
}
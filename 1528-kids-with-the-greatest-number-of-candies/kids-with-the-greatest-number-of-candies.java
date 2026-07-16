class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean>ans=new ArrayList<>();
        int max=0;
        int c=0;
        for(int i:candies){
            max=Math.max(max,i);
        }
        for(int i=0;i<candies.length;i++){
          c=candies[i]+extraCandies;
          if(c>=max){
           ans.add(true);
          } else{
ans.add(false); }
        }
       return ans;
    }
}
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        boolean [] visited=new boolean[nums.length];
     Arrays.sort(nums);   
     backtrack(nums,ans,path,visited);
     return ans;
    }
    public void backtrack(int[]nums,List<List<Integer>> ans,List<Integer>path,boolean[]visited){


if(path.size()==nums.length){
    ans.add(new ArrayList<>(path));
    return;
}
for(int i=0;i<nums.length;i++){
    if(visited[i]){
    continue;
    }
    if(i>0 && nums[i-1] == nums[i] && visited[i-1]==false){
        continue;
    }

path.add(nums[i]);
visited[i]=true;
backtrack(nums,ans,path,visited);
path.remove(path.size()-1);
visited[i]=false;
}
    }
}
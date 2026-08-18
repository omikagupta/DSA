class Solution {
    public List<List<Integer>> subsets(int[] nums) {
List<List<Integer>> ans =new ArrayList<>();
List<Integer> path=new ArrayList<>(); 
backtrack(0,nums,ans,path);
return ans;
    }
    public  void backtrack(int index,int[] nums,List<List<Integer>> ans,List<Integer> path){
        if(index == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[index]);
        backtrack(index+1,nums,ans,path);
        path.remove(path.size()-1);
        backtrack(index+1,nums,ans,path); 
    }
}
class Solution {
    public List<String> generateParenthesis(int n) {
         List<String> ans =new ArrayList<>();
         StringBuilder path= new StringBuilder();
         backtrack(n,0,0,ans,path);
         return ans;
    }
    void backtrack(int n,int open,int close, List<String> ans,StringBuilder path){
        if(open == n && close == n){
            ans.add(path.toString());
            return;
        }
if(open<n){
    path.append('(');
    backtrack(n,open+1,close,ans,path);
    path.deleteCharAt(path.length()-1);
}
if(close<open){
    path.append(')');
    backtrack(n,open,close+1,ans,path);
    path.deleteCharAt(path.length()-1);
}
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int answer=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return answer;
    }
    public int dfs(TreeNode node){
        if(node == null) return 0;
        int left=dfs(node.left);
        int right=dfs(node.right);
      int temp = node.val + Math.max(0, Math.max(left, right));
        int path=node.val+Math.max(0, left) + Math.max(0, right);
                 answer=Math.max(answer,path);
                return temp;
    }
}
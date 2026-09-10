class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return count;
    }

    // Returns int[] where index 0 is subtree sum and index 1 is subtree node count
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int sum = left[0] + right[0] + node.val;
        int nodeCount = left[1] + right[1] + 1;

        if (node.val == sum / nodeCount) {
            count++;
        }

        return new int[]{sum, nodeCount};
    }
}
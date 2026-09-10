class Solution {
    int count = 0;

    // Returns a pair: {subtree_sum, subtree_node_count}
    pair<int, int> postOrder(TreeNode* node) {
        if (!node) {
            return {0, 0};
        }

        auto [leftSum, leftCount] = postOrder(node->left);
        auto [rightSum, rightCount] = postOrder(node->right);

        int sum = leftSum + rightSum + node->val;
        int nodeCount = leftCount + rightCount + 1;

        if (node->val == sum / nodeCount) {
            count++;
        }

        return {sum, nodeCount};
    }

public:
    int averageOfSubtree(TreeNode* root) {
        postOrder(root);
        return count;
    }
};
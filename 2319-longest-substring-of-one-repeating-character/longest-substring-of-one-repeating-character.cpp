class Solution {
public:

    struct Node {
        int len;
        int left, right, best;
        char leftChar, rightChar;
    };

    vector<Node> tree;
    string s;

    Node merge(Node a, Node b) {

        Node res;

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Prefix
        res.left = a.left;

        if (a.left == a.len &&
            a.rightChar == b.leftChar) {

            res.left = a.len + b.left;
        }

        // Suffix
        res.right = b.right;

        if (b.right == b.len &&
            a.rightChar == b.leftChar) {

            res.right = b.len + a.right;
        }

        // Best answer
        res.best = max(a.best, b.best);

        // Combine suffix of left + prefix of right
        if (a.rightChar == b.leftChar) {

            res.best = max(
                res.best,
                a.right + b.left
            );
        }

        return res;
    }

    void build(int node, int l, int r) {

        // Leaf
        if (l == r) {

            tree[node] = {
                1,      // len
                1,      // left
                1,      // right
                1,      // best
                s[l],   // leftChar
                s[l]    // rightChar
            };

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    void update(int node, int l, int r, int index) {

        // Leaf
        if (l == r) {

            tree[node] = {
                1,
                1,
                1,
                1,
                s[l],
                s[l]
            };

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {

            update(node * 2, l, mid, index);

        } else {

            update(node * 2 + 1, mid + 1, r, index);
        }

        // Recalculate parent
        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    vector<int> longestRepeating(
        string s,
        string queryCharacters,
        vector<int>& queryIndices
    ) {

        this->s = s;

        int n = s.length();

        tree.resize(4 * n);

        // Build tree
        build(1, 0, n - 1);

        vector<int> ans;

        for (int i = 0; i < queryIndices.size(); i++) {

            int index = queryIndices[i];

            // Change character
            this->s[index] = queryCharacters[i];

            // Update tree
            update(1, 0, n - 1, index);

            // Root contains answer
            ans.push_back(tree[1].best);
        }

        return ans;
    }
};
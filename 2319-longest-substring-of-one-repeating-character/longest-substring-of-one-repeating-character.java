class Solution {

    class Node {
        int len;
        int left, right, best;
        char leftChar, rightChar;

        Node(int len, int left, int right, int best,
             char leftChar, char rightChar) {

            this.len = len;
            this.left = left;
            this.right = right;
            this.best = best;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s,
                                  String queryCharacters,
                                  int[] queryIndices) {

        // Convert String into char array
        this.s = s.toCharArray();

        int n = s.length();

        tree = new Node[4 * n];

        // Build segment tree
        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];

            // ❌ s[index] = ...
            // ✅ Modify the character array
            this.s[index] = queryCharacters.charAt(i);

            // Update segment tree
            update(1, 0, n - 1, index);

            // Root contains answer for entire string
            ans[i] = tree[1].best;
        }

        return ans;
    }

    void build(int node, int l, int r) {

        // Leaf node
        if (l == r) {

            tree[node] = new Node(
                1,
                1,
                1,
                1,
                s[l],
                s[l]
            );

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

        // Reached the character that changed
        if (l == r) {

            tree[node] = new Node(
                1,
                1,
                1,
                1,
                s[l],
                s[l]
            );

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {

            update(node * 2, l, mid, index);

        } else {

            update(node * 2 + 1, mid + 1, r, index);
        }

        // Recalculate current node
        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    Node merge(Node a, Node b) {

        Node res = new Node(
            a.len + b.len,
            0,
            0,
            0,
            a.leftChar,
            b.rightChar
        );

        // -------------------------
        // 1. PREFIX
        // -------------------------

        res.left = a.left;

        if (a.left == a.len &&
            a.rightChar == b.leftChar) {

            res.left = a.len + b.left;
        }

        // -------------------------
        // 2. SUFFIX
        // -------------------------

        res.right = b.right;

        if (b.right == b.len &&
            a.rightChar == b.leftChar) {

            res.right = b.len + a.right;
        }

        // -------------------------
        // 3. BEST
        // -------------------------

        res.best = Math.max(a.best, b.best);

        // If boundary characters are same,
        // suffix of a + prefix of b can combine

        if (a.rightChar == b.leftChar) {

            res.best = Math.max(
                res.best,
                a.right + b.left
            );
        }

        return res;
    }
}
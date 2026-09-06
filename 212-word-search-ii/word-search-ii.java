class Solution {

 
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        List<String> ans = new ArrayList<>();

     
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.word = word;
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                backtrack(board, row, col, root, ans);
            }
        }

        return ans;
    }

    void backtrack(
        char[][] board,
        int row,
        int col,
        TrieNode node,
        List<String> ans
    ) {

      
        if (row < 0 || col < 0 ||
            row >= board.length ||
            col >= board[0].length) {
            return;
        }

    
        if (board[row][col] == '#') {
            return;
        }

        char ch = board[row][col];

        if (node.children[ch - 'a'] == null) {
            return;
        }


        node = node.children[ch - 'a'];

        
        if (node.word != null) {
            ans.add(node.word);

          
            node.word = null;
        }

        
        board[row][col] = '#';

     
        backtrack(board, row + 1, col, node, ans);

       
        backtrack(board, row - 1, col, node, ans);

        
        backtrack(board, row, col + 1, node, ans);

     
        backtrack(board, row,    col - 1, node, ans);

     
        board[row][col] = ch;
    }
}


class Solution {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> ans = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(board, 0, n, ans);

        return ans;
    }


    void backtrack(
        char[][] board,
        int row,
        int n,
        List<List<String>> ans
    ) {

        if (row == n) {

            List<String> solution = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                solution.add(new String(board[i]));
            }

            ans.add(solution);
            return;
        }


        for (int col = 0; col < n; col++) {

            if (!isSafe(board, row, col)) {
                continue;
            }

            board[row][col] = 'Q';

    
            backtrack(board, row + 1, n, ans);

        
            board[row][col] = '.';
        }
    }


    boolean isSafe(char[][] board, int row, int col) {

        for (int r = 0; r < row; r++) {

            if (board[r][col] == 'Q') {
                return false;
            }
        }


        for (int r = row - 1, c = col - 1;
             r >= 0 && c >= 0;
             r--, c--) {

            if (board[r][c] == 'Q') {
                return false;
            }
        }


        for (int r = row - 1, c = col + 1;
             r >= 0 && c < board.length;
             r--, c++) {

            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
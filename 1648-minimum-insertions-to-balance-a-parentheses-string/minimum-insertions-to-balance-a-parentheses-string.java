class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0; // Tracks needed ')' pairs (each '(' requires two ')')
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                openCount += 2;
                // If openCount is odd, we had a single ')' that needed a matching ')'
                if (openCount % 2 != 0) {
                    insertions++; // Insert one ')'
                    openCount--;   // Decrement to make openCount even again
                }
            } else { // c == ')'
                openCount--;
                // If openCount becomes negative, it means we found a ')' without a matching '('
                if (openCount < 0) {
                    insertions++; // Insert one '('
                    openCount += 2; // The inserted '(' needs two ')'
                }
            }
        }
        
        // Add remaining needed ')' characters
        return insertions + openCount;
    }
}
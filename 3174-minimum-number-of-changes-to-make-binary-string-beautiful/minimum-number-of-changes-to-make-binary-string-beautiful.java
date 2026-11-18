class Solution {
    public int minChanges(String s) {
        int changes = 0;
        
        // Check adjacent pairs of characters
        for (int i = 0; i < s.length(); i += 2) {
            // If the two characters in the pair don't match, we need 1 change
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        
        return changes;
    }
}
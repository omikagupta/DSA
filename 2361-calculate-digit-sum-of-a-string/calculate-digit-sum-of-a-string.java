class Solution {
    public String digitSum(String s, int k) {
        // Repeat the rounds as long as the length of s is greater than k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Divide s into consecutive groups of size k
            for (int i = 0; i < s.length(); i += k) {
                int end = Math.min(i + k, s.length());
                String group = s.substring(i, end);
                
                // Calculate the digit sum of the current group
                int sum = 0;
                for (int j = 0; j < group.length(); j++) {
                    sum += group.charAt(j) - '0';
                }
                
                // Append the sum string to our new result
                nextRound.append(sum);
            }
            
            // Update s with the newly merged string for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}
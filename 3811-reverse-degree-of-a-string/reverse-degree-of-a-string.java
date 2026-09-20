class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int reversedAlphabetPos = 'z' - c + 1; // 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int stringPos = i + 1;                 // 1-indexed string position
            totalDegree += reversedAlphabetPos * stringPos;
        }
        return totalDegree;
    }
}
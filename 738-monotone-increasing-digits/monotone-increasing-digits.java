class Solution {
    public int monotoneIncreasingDigits(int n) {

        char[] ch = String.valueOf(n).toCharArray();

        int marker = ch.length;

        for (int i = ch.length - 1; i > 0; i--) {
            if (ch[i] < ch[i - 1]) {
                ch[i - 1]--;
                marker = i;
            }
        }

        for (int i = marker; i < ch.length; i++) {
            ch[i] = '9';
        }

        return Integer.parseInt(new String(ch));
    }
}
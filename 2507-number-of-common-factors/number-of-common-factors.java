class Solution {
    public int commonFactors(int a, int b) {
        int gcd = gcd(a, b);
        int count = 0;

        for (int i = 1; i * i <= gcd; i++) {
            if (gcd % i == 0) {
                count++;

                if (i != gcd / i) {
                    count++;
                }
            }
        }

        return count;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
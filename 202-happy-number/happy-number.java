import java.util.HashSet;

class Solution {
    HashSet<Integer> set = new HashSet<>();

    public boolean isHappy(int n) {

        if (n == 1)
            return true;

        if (set.contains(n))
            return false;

        set.add(n);

        int next = sumSquare(n);

        return isHappy(next);
    }

    public int sumSquare(int n) {

        if (n == 0)
            return 0;

        int rem = n % 10;

        return rem * rem + sumSquare(n / 10);
    }
}
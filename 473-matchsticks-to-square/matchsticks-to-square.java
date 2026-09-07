
class Solution {

    public boolean makesquare(int[] matchsticks) {

        int sum = 0;

        for (int s : matchsticks) {
            sum += s;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int target = sum / 4;

        Arrays.sort(matchsticks);

        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }

        int[] sides = new int[4];

        return backtrack(matchsticks, 0, sides, target);
    }

    boolean backtrack(
        int[] matchsticks,
        int index,
        int[] sides,
        int target
    ) {

 
        if (index == matchsticks.length) {
            return true;
        }

        int num = matchsticks[index];

        for (int i = 0; i < 4; i++) {

           
            if (sides[i] + num > target) {
                continue;
            }

            if (sides[i] == 0) {

                sides[i] += num;

                if (backtrack(matchsticks, index + 1, sides, target)) {
                    return true;
                }

                sides[i] -= num;

                break;
            }

            sides[i] += num;

           
            if (backtrack(matchsticks, index + 1, sides, target)) {
                return true;
            }

            sides[i] -= num;
        }

        return false;
    }
}

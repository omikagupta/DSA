class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cBoth = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        long totalCost = 0;
        
        // 1. Handle the overlapping requirement using min(costBoth, cost1 + cost2)
        long common = Math.min(n1, n2);
        totalCost += common * Math.min(cBoth, c1 + c2);
        
        n1 -= common;
        n2 -= common;
        
        // 2. Handle remaining requirement for type 1
        if (n1 > 0) {
            totalCost += n1 * Math.min(c1, cBoth);
        }
        
        // 3. Handle remaining requirement for type 2
        if (n2 > 0) {
            totalCost += n2 * Math.min(c2, cBoth);
        }
        
        return totalCost;
    }
}
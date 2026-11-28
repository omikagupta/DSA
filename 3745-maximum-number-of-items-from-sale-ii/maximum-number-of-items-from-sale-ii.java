import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;
        
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }
        
        // Precompute number of multiples for each factor f in [1, n]
        int[] multiplesCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multiplesCount[f] += freq[m];
            }
        }
        
        // Pair each item's price with its gain (eligible distinct free items)
        int[][] itemData = new int[n][2];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int p = items[i][1];
            itemData[i][0] = p;
            itemData[i][1] = multiplesCount[f] - 1; // exclude itself
        }
        
        // Sort items by price ascending
        Arrays.sort(itemData, (a, b) -> Integer.compare(a[0], b[0]));
        
        long totalItems = 0;
        long remBudget = budget;
        
        // Best answer if we spend all remaining budget on the cheapest item (value-1 units)
        long maxAns = remBudget / minPrice;
        
        for (int[] data : itemData) {
            long price = data[0];
            long gain = data[1];
            
            if (gain == 0) continue;
            if (remBudget < price) break;
            
            // If 1 boosted copy (2 items) is more expensive than 2 plain copies of minPrice, stop
            if (price >= 2L * minPrice) break;
            
            long canBuy = Math.min(gain, remBudget / price);
            totalItems += canBuy * 2;
            remBudget -= canBuy * price;
            
            // Update maxAns considering spending the rest of the budget on minPrice
            maxAns = Math.max(maxAns, totalItems + remBudget / minPrice);
        }
        
        return (int) maxAns;
    }
}
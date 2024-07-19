package homework2;

// Bryan Bergo 7-18-24
public class BestTimeToBuyAndSellStock {
    public static int bestTimeToBuyAndSellStock(int[] prices) {
        return divideAndConquer(prices, 0, prices.length - 1);
    }

    private static int divideAndConquer(int[] prices, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;

        // Find the maximum profit in the left subarray
        int leftProfit = divideAndConquer(prices, start, mid);

        // Find the maximum profit in the right subarray
        int rightProfit = divideAndConquer(prices, mid + 1, end);

        // Find the maximum profit by combining the left and right subarrays
        int crossProfit = findCrossProfit(prices, start, mid, end);

        // Return the maximum of the three profits
        return Math.max(Math.max(leftProfit, rightProfit), crossProfit);
    }

    private static int findCrossProfit(int[] prices, int start, int mid, int end) {
        int leftMinPrice = Integer.MAX_VALUE;
        int leftMaxProfit = 0;

        // Find the minimum price and maximum profit in the left half
        for (int i = mid; i >= start; i--) {
            if (prices[i] < leftMinPrice) {
                leftMinPrice = prices[i];
            } else if (prices[i] - leftMinPrice > leftMaxProfit) {
                leftMaxProfit = prices[i] - leftMinPrice;
            }
        }

        int rightMinPrice = Integer.MAX_VALUE;
        int rightMaxProfit = 0;

        // Find the minimum price and maximum profit in the right half
        for (int i = mid + 1; i <= end; i++) {
            if (prices[i] < rightMinPrice) {
                rightMinPrice = prices[i];
            } else if (prices[i] - rightMinPrice > rightMaxProfit) {
                rightMaxProfit = prices[i] - rightMinPrice;
            }
        }

        // Return the maximum profit by combining the left and right profits
        return leftMaxProfit + rightMaxProfit;
    }
}

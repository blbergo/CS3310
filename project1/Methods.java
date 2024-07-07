package project1;

import java.util.ArrayList;

public class Methods {
    public static int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static MaxSumResult exhuastive(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }

        return new MaxSumResult(maxSum, startIndex, endIndex, "Exhuastive");
    }

    public static MaxSumResult quadratic(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }

        return new MaxSumResult(maxSum, startIndex, endIndex, "Quadratic");
    }

    public static MaxSumResult divideAndConquer(int[] arr, int left, int right) {
        if (left == right) {
            return new MaxSumResult(arr[left], left, right, "Divide and Conquer");
        }

        int center = (left + right) / 2;
        MaxSumResult maxLeftSum = divideAndConquer(arr, left, center);
        MaxSumResult maxRightSum = divideAndConquer(arr, center + 1, right);

        int maxLeftBorderSum = Integer.MIN_VALUE, leftBorderSum = 0;
        int leftIndex = center;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
                leftIndex = i;
            }
        }

        int maxRightBorderSum = Integer.MIN_VALUE, rightBorderSum = 0;
        int rightIndex = center + 1;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
                rightIndex = i;
            }
        }

        int maxSum = max3(maxLeftSum.maxSum, maxRightSum.maxSum, maxLeftBorderSum + maxRightBorderSum);
        if (maxSum == maxLeftSum.maxSum) {
            return maxLeftSum;
        } else if (maxSum == maxRightSum.maxSum) {
            return maxRightSum;
        } else {
            return new MaxSumResult(maxLeftBorderSum + maxRightBorderSum, leftIndex, rightIndex, "Divide and Conquer");
        }

    }

    public static MaxSumResult dynamicProgramming(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        ArrayList<Integer> dp = new ArrayList<Integer>(arr.length);

        dp.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            dp.add(Math.max(arr[i], dp.get(i - 1) + arr[i]));
        }

        for (int i = 0; i < dp.size(); i++) {
            if (dp.get(i) > maxSum) {
                startIndex = endIndex;
                maxSum = dp.get(i);
                endIndex = i;
            }
        }

        return new MaxSumResult(maxSum, startIndex, endIndex, "Dynamic Programming");
    }
}

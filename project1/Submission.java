import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void testMethod(MethodType method, int[] arr) {
        MaxSumResult result = null;
        switch (method) {
            case EXHUASTIVE:
                // measuring the time as depicted in the project docx - surrounding the function
                // execution, but not including the switch statement
                long start = System.nanoTime();
                result = Methods.exhuastive(arr);
                long end = System.nanoTime();
                result.setTimeElapsed(end - start);
                break;
            case QUADRATIC:
                start = System.nanoTime();
                result = Methods.quadratic(arr);
                end = System.nanoTime();
                result.setTimeElapsed(end - start);
                break;
            case DIVIDE_AND_CONQUER:
                start = System.nanoTime();
                result = Methods.divideAndConquer(arr, 0, arr.length - 1);
                end = System.nanoTime();
                result.setTimeElapsed(end - start);
                break;
            case DYANMIC_PROGRAMMING:
                start = System.nanoTime();
                result = Methods.dynamicProgramming(arr);
                end = System.nanoTime();
                result.setTimeElapsed(end - start);
                break;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        System.out.println("Iteration 1\n---------------------------");
        int[] arr = DataGen.generateInput();
        System.out.println("Generated input:");
        System.out.println(Arrays.toString(arr));

        testMethod(MethodType.EXHUASTIVE, arr);
        testMethod(MethodType.QUADRATIC, arr);
        testMethod(MethodType.DIVIDE_AND_CONQUER, arr);
        testMethod(MethodType.DYANMIC_PROGRAMMING, arr);

        System.out.println("\nIteration 2\n---------------------------");
        int[] arr2 = DataGen.generateInput();
        System.out.println("Generated input:");
        System.out.println(Arrays.toString(arr2));

        testMethod(MethodType.EXHUASTIVE, arr2);
        testMethod(MethodType.QUADRATIC, arr2);
        testMethod(MethodType.DIVIDE_AND_CONQUER, arr2);
        testMethod(MethodType.DYANMIC_PROGRAMMING, arr2);
    }
}


public class DataGen {
    public int[] data;

    public static int[] generateInput() {
        int[] arr = new int[100];
        int MIN = -100;
        int MAX = 100;
        int RANGE = MAX - MIN;

        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(RANGE) + MIN;
        }
        return arr;
    }
}

public class MaxSumResult {
    public int maxSum;
    public int startIndex;
    public int endIndex;
    public long timeElapsed;
    public String methodName;

    public MaxSumResult(int maxSum, int startIndex, int endIndex, String methodName) {
        this.maxSum = maxSum;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.methodName = methodName;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String toString() {
        return String.format(
                "---------------------------\nMethod: %s\nMax Sum: %d\nStart Index: %d\nEnd Index: %d\nTime Elapsed: %d ns",
                methodName, maxSum, startIndex, endIndex, timeElapsed);
    }
}

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

public enum MethodType {
    EXHUASTIVE,
    QUADRATIC,
    DIVIDE_AND_CONQUER,
    DYANMIC_PROGRAMMING,
}
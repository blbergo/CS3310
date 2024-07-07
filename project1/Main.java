package project1;

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

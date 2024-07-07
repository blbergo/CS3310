package project1;

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
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = { -2, 11, -4, 13, -5, -2 };
        testMethod(MethodType.EXHUASTIVE, arr);
        testMethod(MethodType.QUADRATIC, arr);
        testMethod(MethodType.DIVIDE_AND_CONQUER, arr);
    }
}

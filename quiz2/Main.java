package quiz2;

import java.util.HashSet;

public class Main {
    // Bryan Bergo 7/20/24
    public static boolean nSquaredSolution(int[] arr, int i, int j, int v) {
        for (int k = 0; k < arr.length; k++) {
            for (int l = k; l < arr.length; l++) {
                if (arr[l] + arr[k] == v) {
                    return true;
                }
            }
        }
        return false;
    }

    // Bryan Bergo 7/20/24
    public static boolean divideAndConquer(int[] arr, int i, int j, int v) {
        if (i == j) {
            return false;
        }
        int mid = (i + j) / 2;
        if (arr[mid] + arr[mid + 1] == v) {
            return true;
        }
        return divideAndConquer(arr, i, mid, v) || divideAndConquer(arr, mid + 1, j, v);
    }

    // Bryan Bergo 7/20/24
    public static boolean optimizedSolution(int[] arr, int v) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(v - arr[i])) {
                System.out.println("Pair found: " + arr[i] + " " + (v - arr[i]));
                return true;
            }
            set.add(arr[i]);
        }
        return false;
    }
}

package homework2;

import java.util.Arrays;

// Bryan Bergo - 7/18/24
public class MaxDifferencePartition {

  public static int[][] findPartition(int[] arr) {
    if (arr.length == 2) {
      return new int[][] { { arr[0] }, { arr[1] } }; // Base case: return the two elements as separate sublists
    }

    // Sort the array in descending order
    Arrays.sort(arr);

    int[] sublist1 = new int[arr.length / 2];
    int[] sublist2 = new int[arr.length / 2];

    // Fill sublist1 with alternating elements (even indices)
    for (int i = 0; i < arr.length / 2; i++) {
      sublist1[i] = arr[2 * i];
    }

    // Fill sublist2 with remaining elements (odd indices)
    for (int i = 0; i < arr.length / 2; i++) {
      sublist2[i] = arr[2 * i + 1];
    }

    return new int[][] { sublist1, sublist2 }; // Return the sublists as a 2D array
  }
}
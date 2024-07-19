package homework2;

// Bryan Bergo - 7/18/24
public class SearchSorted2DMatrix {
    public static int[] findElement(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;

        // binary search in 2D matrix
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return new int[] { row, col };
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }

        // value not found in matrix
        return new int[] { -1, -1 };
    }
}

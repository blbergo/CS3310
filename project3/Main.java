package project3;

import java.util.Arrays;

public class Main {
    // Bryan Bergo - 7/22/24
    // Time complexity: O(n*m)
    public static int maxTotalValue(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] dp = new int[n][m];

        // base case
        dp[0][0] = board[0][0];

        // calculate first row
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + board[i][0];
        }

        // calculate first column
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + board[0][j];
        }

        // fill in the rest of the table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + board[i][j];
            }
        }

        // return the value of the bottom-right cell
        return dp[n - 1][m - 1];
    }

    // helper function to generate random data
    public static int[][] generateData(int n, int m) {
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = (int) (Math.random() * 100);
            }
        }
        return board;
    }

    // helper function to print the board
    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] board = generateData(3, 3);
        int[][] board2 = generateData(4, 4);
        int[][] board3 = generateData(5, 5);

        System.out.println("Max value: " + maxTotalValue(board) + " for board:");
        printBoard(board);

        System.out.println("Max value: " + maxTotalValue(board2) + " for board:");
        printBoard(board2);

        System.out.println("Max value: " + maxTotalValue(board3) + " for board:");
        printBoard(board3);
    }

}

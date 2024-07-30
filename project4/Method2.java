package project4;

import java.util.ArrayList;

public class Method2 {
    // Bryan Bergo - 7/29/24
    public static int[] topSort(int[][] A) {
        int n = A.length;
        int[] inDegree = new int[n];

        // calculate in-degree
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inDegree[j] += A[i][j];
            }
        }

        int[] TS = new int[n];
        int index = 0;

        // topological sort
        while (index < n) {
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    TS[index++] = i;
                    inDegree[i] = -1;
                    for (int j = 0; j < n; j++) {
                        if (A[i][j] == 1) {
                            inDegree[j]--;
                        }
                    }
                }
            }
        }
        return TS;
    }

    public static void computeTimes(ArrayList<Integer> ES, ArrayList<Integer> EF, ArrayList<Integer> LS,
            ArrayList<Integer> LF, ArrayList<Integer> T, int[][] A, int[] TS) {
        int n = T.size();

        // calculate ES and EF
        for (int i = 0; i < n; i++) {
            int task = TS[i];
            ES.add(0);
            EF.add(0);
            LS.add(0);
            LF.add(0);
            for (int j = 0; j < n; j++) {
                if (A[j][task] == 1) {
                    ES.set(task, Math.max(ES.get(task), EF.get(j)));
                }
            }
            EF.set(task, ES.get(task) + T.get(task));
        }

        // calculate LS and LF
        for (int i = n - 1; i >= 0; i--) {
            int task = TS[i];
            if (A[task][task] == 0) {
                LF.set(task, EF.get(task));
                LS.set(task, LF.get(task) - T.get(task));
            } else {
                LF.set(task, Integer.MAX_VALUE);
                for (int j = 0; j < n; j++) {
                    if (A[task][j] == 1) {
                        LF.set(task, Math.min(LF.get(task), LS.get(j)));
                    }
                }
                LS.set(task, LF.get(task) - T.get(task));
            }
        }

        // print the results
        for (int i = 0; i < n; i++) {
            System.out.println("Task " + i + ": ES = " + ES.get(i) + ", EF = " + EF.get(i) + ", LS = " + LS.get(i)
                    + ", LF = " + LF.get(i));
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> ES = new ArrayList<>();
        ArrayList<Integer> EF = new ArrayList<>();
        ArrayList<Integer> LS = new ArrayList<>();
        ArrayList<Integer> LF = new ArrayList<>();
        ArrayList<Integer> T = new ArrayList<>() {
            {
                add(2);
                add(4);
                add(5);
                add(9);
                add(3);
                add(2);
                add(1);
                add(10);
                add(11);
                add(6);
                add(9);
                add(8);
                add(7);

            }
        };

        int[][] A = new int[T.size()][T.size()];
        A[0][1] = 1;
        A[0][2] = 1;
        A[0][3] = 1;
        A[1][4] = 1;
        A[1][5] = 1;
        A[2][5] = 1;
        A[3][5] = 1;
        A[4][6] = 1;
        A[5][6] = 1;
        A[5][7] = 1;
        A[3][8] = 1;
        A[6][9] = 1;
        A[7][9] = 1;
        A[8][10] = 1;
        A[9][11] = 1;
        A[9][12] = 1;
        A[10][12] = 1;
        A[11][12] = 1;

        int[] TS = topSort(A);

        computeTimes(ES, EF, LS, LF, T, A, TS);

    }

}

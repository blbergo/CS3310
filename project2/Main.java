package project2;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static int findKthSmallestProcedure1(int k, ArrayList<Integer> S) {
        if (S.size() == 1) {
            return S.get(0);
        }

        Random rand = new Random();
        int m = S.get(rand.nextInt(S.size()));

        // less than m
        ArrayList<Integer> S1 = new ArrayList<>();
        // equal to m
        ArrayList<Integer> S2 = new ArrayList<>();
        // greater than m
        ArrayList<Integer> S3 = new ArrayList<>();

        for (int i = 0; i < S.size(); i++) {
            if (S.get(i) < m) {
                S1.add(S.get(i));
            } else if (S.get(i) == m) {
                S2.add(S.get(i));
            } else {
                S3.add(S.get(i));
            }
        }

        if (S1.size() >= k) {
            return findKthSmallestProcedure1(k, S1);
        }

        if (S1.size() + S2.size() >= k) {
            return m;
        }

        return findKthSmallestProcedure1(k - S1.size() - S2.size(), S3);

    }

    public static int findKthSmallestProcedure2(int k, ArrayList<Integer> S) {
        if (S.size() < 50) {
            S.sort(null);
            return S.get(k - 1);
        }

        // divide S into n/5 groups of 5 elements each
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < S.size(); i += 5) {
            ArrayList<Integer> group = new ArrayList<>();
            for (int j = i; j < i + 5 && j < S.size(); j++) {
                group.add(S.get(j));
            }
            groups.add(group);
        }

        // sort each group
        for (ArrayList<Integer> group : groups) {
            group.sort(null);
        }

        // find the median of each group
        ArrayList<Integer> medians = new ArrayList<>();
        for (ArrayList<Integer> group : groups) {
            medians.add(group.get(group.size() / 2));
        }

        int m = findKthSmallestProcedure2(medians.size() / 2, medians);

        // less than m
        ArrayList<Integer> S1 = new ArrayList<>();
        // equal to m
        ArrayList<Integer> S2 = new ArrayList<>();
        // greater than m
        ArrayList<Integer> S3 = new ArrayList<>();

        for (int i = 0; i < S.size(); i++) {
            if (S.get(i) < m) {
                S1.add(S.get(i));
            } else if (S.get(i) == m) {
                S2.add(S.get(i));
            } else {
                S3.add(S.get(i));
            }
        }

        if (S1.size() >= k) {
            return findKthSmallestProcedure2(k, S1);
        } else if (S1.size() + S2.size() >= k) {
            return findKthSmallestProcedure1(k, S1);
        } else {
            return findKthSmallestProcedure2(k - S1.size() - S2.size(), S3);
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> S = new ArrayList<>();
        ArrayList<Integer> S2 = new ArrayList<>();

        Random rand = new Random();
        for (int i = 1; i < 100; i++) {
            S.add(rand.nextInt(1000));
            S2.add(rand.nextInt(1000));
        }

        double procedure1Start = System.nanoTime();
        int kthSmallest1 = findKthSmallestProcedure1(1, S);
        double procedure1End = System.nanoTime();
        System.out
                .println("Procedure 1 Data: " + S.toString() + "\n Procedure 1 Result: " + kthSmallest1 + " Time: "
                        + (procedure1End - procedure1Start) + " ns");

        double procedure2Start = System.nanoTime();
        int kthSmallest2 = findKthSmallestProcedure2(20, S2);
        double procedure2End = System.nanoTime();
        System.out
                .println("Procedure 2 Data: " + S2.toString() + "\n Procedure 2 Result: " + kthSmallest2 + " Time: "
                        + (procedure2End - procedure2Start) + " ns");
    }

}

package project1;

import java.util.Random;

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

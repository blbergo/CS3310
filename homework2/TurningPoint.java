package homework2;

// Bryan Bergo - 7/18/24
public class TurningPoint {
    public static int findTurningPoint(int[] arr) {
        return findTurningPointUtil(arr, 0, arr.length - 1);
    }

    private static int findTurningPointUtil(int[] arr, int low, int high) {
        // found the turning point, base case
        if (low == high) {
            return low;
        }

        int mid = (low + high) / 2;

        if (arr[mid] > arr[mid + 1]) {
            // search the left side
            return findTurningPointUtil(arr, low, mid);
        } else {
            // search the right side
            return findTurningPointUtil(arr, mid + 1, high);
        }
    }
}

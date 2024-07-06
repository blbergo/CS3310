import java.util.HashSet;
import java.util.Set;

class Main {
    // Bryan Bergo 7/6/24
    public static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;

    }

    // runs in O(nlogn) time
    public static int[] intersection(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        int index = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (binarySearch(nums2, nums1[i])) {
                result[index] = nums1[i];
                index++;
            }
        }
        return result;
    }

    // Bryan Bergo 7/6/24 - runs in O(n + m) time
    public static int[] intersectionUnsorted(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        // O(n)
        for (int i = 0; i < nums1.length; i++) {
            // hashset add is O(1)
            set.add(nums1[i]);
        }

        // O(m)
        for (int i = 0; i < nums2.length; i++) {
            // hashset lookup is O(1)
            if (set.contains(nums2[i])) {
                // hashset add is O(1)
                intersection.add(nums2[i]);
            }
        }

        int[] result = new int[intersection.size()];
        int index = 0;

        // O(n + m)
        for (int num : intersection) {
            result[index] = num;
            index++;
        }

        return result;
    }
}
package palomino.algorithms;

import java.util.Arrays;

public class QuickSort {
    public static int[] quickSort(int[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private static int[] sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            sort(arr, low, pivotIndex - 1);
            sort(arr, pivotIndex + 1, high);
        }
        return arr;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low -1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        int tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;

        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(quickSort(new int[]{6, 1, 5, 3, 2, 4})));
    }
}

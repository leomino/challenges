package palomino.algorithms;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] unsorted) {
        if (unsorted.length <= 1) {
            return unsorted;
        }
        int middle = unsorted.length / 2;

        int[] left =  mergeSort(Arrays.copyOfRange(unsorted, 0, middle));
        int[] right =  mergeSort(Arrays.copyOfRange(unsorted, middle, unsorted.length));

        return merge(left, right);
    }

    private static int[] merge(int[] first, int[] second) {
        int i = 0;
        int j = 0;
        int[] result = new int[first.length + second.length];

        for (int k = 0; k < result.length; k++) {
            if (j >= second.length || i < first.length && first[i] < second[j]) {
                result[k] = first[i];
                i++;
            } else {
                result[k] = second[j];
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[]{6, 2, 8, 4, 9, 239482934, 123, -5, -2174})));
    }
}

/*
 * MIT License
 *
 * Copyright (c) 2022 Leonardo Palomino
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package palomino.foobar.level_2;

import java.util.Arrays;

/**
 * Sorts a {@code String[]} of one or more 'version' numbers e.g. '1.11.0' or '1.2' by 'merge-sort'
 *
 * @since 2022-01-22
 * @see <a href="https://de.wikipedia.org/wiki/Mergesort">Mergesort: https://de.wikipedia.org/wiki/Mergesort</a>
 * @author Leonardo Palomino
 */
public class Solution_A {

    /**
     * Sorts the given {@code String[]} of 'version' numbers by merge-sort.
     * @param l the unsorted {@code String[]} of 'version' numbers.
     * @return the sorted {@code String[]} of the versions.
     */
    public static String[] solution(String[] l) {
        if(l.length == 1) return l;

        int mid = l.length/2;
        String[] left = solution(Arrays.copyOfRange(l, 0, mid));
        String[] right = solution(Arrays.copyOfRange(l, mid, l.length));

        return merge(left, right);
    }

    /**
     * The Merging-part of the 'merge-sort' algorithm.
     * <p>Note: It doesn't use Java Collection Framework's implementation of a Queue
     * mainly to avoid the iteration needed when creating the {@link java.util.LinkedList} based on a {@code String[]}.
     * @param left {@code String[]} to be merged with the right.
     * @param right {@code String[]} to be merged with the left.
     * @return the sorted combination of left and right.
     */
    private static String[] merge(String[] left, String[] right) {
        String[] result = new String[left.length+right.length];
        int index = 0;
        int leftHead = 0;
        int rightHead = 0;

        while(!isEmpty(left, leftHead) || !isEmpty(right, rightHead)) {
            String next;
            if(isEmpty(left, leftHead) || !isEmpty(right, rightHead) && isSmaller(right[rightHead], left[leftHead])) {
                next = right[rightHead++];
            } else {
                next = left[leftHead++];
            }
            result[index++] = next;
        }
        return result;
    }

    /**
     * Checks whether the given {@code int} head exceeded the array's length.
     * @param s the {@code String[]} to be checked.
     * @param head the current index.
     * @return true if the queue represented by the array 'is empty', due to the {@code int} head exceeded the length.
     */
    private static boolean isEmpty(String[] s, int head) {
        return head >= s.length;
    }

    /**
     * Compares two 'elevator-versions' {@code String} a and {@code String} b.
     * <p>Creates a {@code String[]} for each passed 'version-number' by splitting the versions by '.'</p>
     * <p>Handles the {@code String[]}s as queues with the head starting at 0 and increasing by each number being compared.
     * When one 'queue' is empty, (see {@link Solution_A#isEmpty(String[], int)}) while the other isn't,
     * the empty one must be the smaller version-number.
     * <p><b>Note: </b>For the sake of keeping the algorithm 'stable' the method will return true if both Strings are the same.
     * @see <a href="https://de.wikipedia.org/wiki/Stabilität_(Sortierverfahren)">
     *     Stable sorting: https://de.wikipedia.org/wiki/Stabilität_(Sortierverfahren)</a>
     * @param a the first {String} version.
     * @param b the second {String} version.
     * @return true if a represents a smaller 'version' than b <b>or</b> if a is equal to b.
     */
    private static boolean isSmaller(String a, String b) {
        if(!a.equals(b)) {
            String[] aQ = a.split("\\.");
            String[] bQ = b.split("\\.");
            int curr = 0;

            while(!isEmpty(aQ, curr) && !isEmpty(bQ, curr)) {
                int comp = Integer.compare(Integer.parseInt(aQ[curr]), Integer.parseInt(bQ[curr++]));
                if(comp != 0) {
                    return comp < 0;
                }
            }
            if (!isEmpty(aQ, curr) || !isEmpty(bQ, curr)) {
                return isEmpty(aQ, curr);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})));
    }
}
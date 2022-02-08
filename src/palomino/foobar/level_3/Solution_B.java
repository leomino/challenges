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

package palomino.foobar.level_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * Calculates all triple-permutations (li, lj, lk) of a list l of integers, where lj divides li and lk divides lj,
 * while the indexes also meet the constraint i < j < k.
 *
 * @author Leonardo Palomino
 * @since 2022-02-07
 */
public class Solution_B {

    /**
     * Calculates all triples meeting the above-mentioned conditions.
     *
     * @param l the list of integers;  has a length between 2 (inclusive) and 1000000 (exclusive).
     * @return the count of all triples (li, lj, lk).
     */
    public static int solution(int[] l) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < l.length; i++) {
            map.put(i, new HashSet<>());
            for (int j = i + 1; j < l.length; j++) {
                if (l[j] % l[i] == 0) {
                    map.get(i).add(j);
                }
            }
        }

        int counter = 0;

        for (Entry<Integer, HashSet<Integer>> e : map.entrySet()) {
            for (Integer child : e.getValue()) {
                counter += map.get(child).size();
            }
        }

        return counter;
    }

}

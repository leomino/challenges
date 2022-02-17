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

package palomino.kickstart.y_2021.round_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Calculates how many operations on a given string is required for the string to get the desired "goodness"-score.
 * <p><i>Charles defines the goodness score of a string as the number of indices i i  such that
 * Si≠SN−i+1 S i ≠ S N − i + 1  where 1≤i≤N/2 1 ≤ i ≤ N / 2  (1 1 -indexed).
 * For example, the string CABABC has a goodness score of 2 2  since S2≠S5 S 2 ≠ S 5  and S3≠S4 S 3 ≠ S 4 .
 * </i>
 *
 * @author Leonardo Palomino
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068cca3">K-Godness String</a>
 * @since 2022-02-17
 **/
public class KGoodnessString {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int K = in.nextInt();
            String s = in.next();
            char[] cArr = s.toCharArray();
            int result = 0;
            for (int j = 0; j < s.length() / 2; j++) {
                if (cArr[j] != cArr[N - 1 - (j)]) {
                    result++;
                }
            }

            System.out.println("Case #" + i + ": " + (Math.max(K, result) - Math.min(K, result)));
        }
    }
}

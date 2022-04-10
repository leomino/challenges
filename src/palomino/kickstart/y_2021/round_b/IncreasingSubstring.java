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

package palomino.kickstart.y_2021.round_b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Calculates the length of all valid substrings in a String S, where a valid substring is a string,
 * where each char C(i) is "greater" than C(i-a).
 * @since 2022-02-19
 * @author Leonardo Palomino
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435a5b/000000000077a882">Increasing Substring</a>
 */
public class IncreasingSubstring {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int last = 0;
            String s = in.next();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                if (j > 0 && s.charAt(j) > s.charAt(j - 1)) {
                    ++last;
                } else {
                    last = 1;
                }
                sb.append(last);
                if (j < N - 1) sb.append(" ");
            }
            System.out.println("Case #" + i + ": " + sb.toString());
        }
    }

}

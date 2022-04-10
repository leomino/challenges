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

package palomino.kickstart.y_2019.round_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


/**
 *
 * Timecomplexity: O(N*P)
 */
public class Training {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int P = in.nextInt();

            Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a*-1));
            for (int j = 0; j < N; j++) {
                pq.offer(in.nextInt());
            }

            int[] skills = new int[N];
            for (int j = 0; j < N; j++) {
                skills[j] = pq.remove();
            }

            int res = Integer.MAX_VALUE;
            int lvl;
            int diff;
            for (int j = 0; j < N-P+1; j++) {
                diff = 0;
                lvl = skills[j];
                for (int k = j; k < j+P; k++) {
                    diff+=lvl-skills[k];
                }
                res = Math.min(diff, res);
            }

            System.out.println("Case #" + i + ": " + res);
        }
    }
}

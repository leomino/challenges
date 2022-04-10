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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LongestProgression {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {

            int N = in.nextInt();

            if(N <= 2) {
                System.out.println("Case #" + i + ": " + N);
                break;
            }

            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = in.nextInt();
            }

            int[] diffs = new int[N-1];
            for (int j = 0; j < diffs.length; j++) {
                diffs[j] = arr[j+1]-arr[j];
            }

            int last = diffs[0];
            Map<Integer, List<Integer>> hm = new HashMap<>();
            List<Integer> l = new ArrayList<>();
            l.add(0);
            hm.put(last, l);
            for (int j = 1; j < diffs.length; j++) {
                if(last == diffs[j]) {
                    hm.get(last).add(j);
                } else {
                    hm.put(diffs[j], new ArrayList<>());
                }
            }

            System.out.println("Case #" + i + ": ");
        }
    }
}

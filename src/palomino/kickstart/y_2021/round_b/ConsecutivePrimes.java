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

public class ConsecutivePrimes {

    private static boolean[] expand(boolean[] prev) {
        boolean[] expanded = new boolean[200];
        for(int i = 0; i<prev.length; i++) {
            if(!prev[i]) {
                int j = 0;
                if(j>=200) continue;
                while(j<200) {
                    expanded[j] = true;
                    j = j+i;
                }
            }
        }
        return expanded;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            long Z = in.nextLong();
            Long curr = 0l;
            long result = 0;

            int prev = 2;
            int j = 3;


            boolean[] sieve = new boolean[]{true, true, false, false, true, false, true, false, true, true, true, false};
            int skip = 0;

            while(true) {
                if(sieve.length<=j) {
                    skip+= sieve.length;
                    sieve = expand(sieve);
                }
                if(!sieve[j-skip]) {
                    curr = Long.valueOf(prev*j+skip);
                    if(curr > Z) break;
                    prev = j+skip;
                }
                result = curr;
                j++;
            }


            System.out.println("Case #" + i + ": " + result);
        }
    }

}

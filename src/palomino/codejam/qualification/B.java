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
package palomino.codejam.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Calculates the amount per color needed from four different printers to form a color consisting of at least 1.000.000 units of ink.
 * <p><b>Note:</b> could also be impossible if the given printers don't have enough ink.
 * @since 2022-04-01
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4672b">3D Printing</a>
 * @author Leonardo Palomino
 */
public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int max = 1000000;
            int[][] printers = new int[3][4];
            int[] constraints = new int[4];

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 4; c++) {
                    int next = in.nextInt();
                    constraints[c] = r == 0 ? next : Math.min(constraints[c], next);
                    printers[r][c] = next;
                }
            }

            if (Arrays.stream(constraints).sum() < max) {
                System.out.println("Case #" + i + ": IMPOSSIBLE");
                continue;
            }

            String[] res = new String[4];
            for (int j = 0; j < 4; j++) {
                if(max <= 0) {
                    res[j] = "0";
                } else {
                    res[j] = constraints[j] >= max ? max + "" : constraints[j] + "";
                    max-=constraints[j];
                }

            }

            System.out.println("Case #" + i + ": " + String.join(" ", res));
        }
    }
}

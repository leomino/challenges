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
import java.util.Scanner;

/**
 * Generates a "Punched Cards" ASCII-style table given colum-count and row-count.
 * <p>example:
 * <pre>
 *      Table 3x3           Table 2x2:
 *          ..+-+-+-+           ..+-+-+
 *          ..+-+-+-+           ..|.|.|
 *          ..|.|.|.|           +-+-+-+
 *          +-+-+-+-+           |.|.|.|
 *          |.|.|.|.|           +-+-+-+
 *          +-+-+-+-+
 * </pre>
 * @since 2022-04-01
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4621b">Punched Cards</a>
 * @author Leonardo Palomino
 */
public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {

            int R = in.nextInt();
            int C = in.nextInt();
            String[][] result = new String[2*R+1][1];

            StringBuilder sb = new StringBuilder();

            String border = "-+";
            String row = ".|";

            String[] blocks = new String[]{border, row};
            String[] starting = new String[]{"+", "|"};

            for (int j = 0; j < R*2+1; j++) {
                if(j < 2) {
                    sb.append(".");
                } else {
                    sb.append(starting[j%2]);
                }
                for (int k = 0; k < C; k++) {
                    if(j == 0 && k == 0) {
                        sb.append(".+");
                    } else {
                        sb.append(blocks[j%2]);
                    }
                }
                result[j][0] = sb.toString();
                sb = new StringBuilder();
            }

            System.out.println("Case #" + i + ":");
            for (String[] strings : result) {
                System.out.println(strings[0]);
            }
        }
    }
}

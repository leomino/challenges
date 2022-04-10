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

package palomino.codejam.archives;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class AppendSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();

            BigInteger last = new BigInteger(String.valueOf(in.nextInt()));
            int sum = 0;
            for (int j = 0; j < N - 1; j++) {
                BigInteger curr = new BigInteger(String.valueOf(in.nextInt()));
                if(curr.compareTo(last) <= 0) {
                    String currStr = String.valueOf(curr);
                    String lastStr = String.valueOf(last);

                    String lastPrefix = lastStr.substring(0, currStr.length());
                    String lastSuffix = lastStr.substring(currStr.length());
                    if(!lastSuffix.isEmpty() && curr.compareTo(new BigInteger(lastPrefix)) == 0 && lastSuffix.charAt(lastSuffix.length()-1) != '9') {
                        String next = new BigInteger(currStr+lastSuffix).add(BigInteger.ONE).toString();
                        sum+=next.length()-currStr.length();
                        curr = new BigInteger(next);
                    } else {
                        StringBuilder sb = new StringBuilder(currStr);
                        int z = lastSuffix.length()+(new BigInteger(lastPrefix).compareTo(curr) >= 0 ? 1 : 0);
                        sum+=z;
                        for (int k = 0; k < z; k++) {
                            sb.append("0");
                        }
                        curr = new BigInteger(sb.toString());
                    }
                }
                last = curr;
            }

            System.out.println("Case #" + i + ": " + sum);
        }
    }
}

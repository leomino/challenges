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
package palomino.foobar.level_1;

/**
 * Calculates a new 'bunny ID' by taking a 5 character long substring from a given index.
 * <p>The substring is taken from a calculated string with all prime numbers concatenated.
 * <p>The calculation is based on Sieve of Eratosthenes.
 *
 * @since 2022-01-19
 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes</a>
 * @author Leonardo Palomino
 */
public class Solution {

    /**
     * Retrieves the 5 digit long substring, beginning at the specified index (inclusive).
     * @param i the index from where to start the substring.
     * @return the 5 digit long substring.
     */
    public static String solution(int i) {
        StringBuilder resultPrimeRange = new StringBuilder();
        Integer start = null;
        int currentLength = 0;
        int currentNumber = 2;

        boolean[] sieveEratosthenes = {true, true, false, false, true, false};

        while(currentLength<=i+5) {
            if(sieveEratosthenes.length<=currentNumber) {
                sieveEratosthenes = expandSieveEratosthenesArray(sieveEratosthenes);
            }
            if(!sieveEratosthenes[currentNumber]) {
                String numberAsString = String.valueOf(currentNumber);
                int tmp = currentLength + numberAsString.length();
                if (tmp >= i) {
                    if (start == null) start = i - currentLength;
                    resultPrimeRange.append(numberAsString);
                }
                currentLength = tmp;
            }
            ++currentNumber;
        }
        return resultPrimeRange.substring(start, start+5);
    }

    /**
     * Expands the given sieve of eratosthenes array by doubling it in size.
     * <p><b>Note:</b> the values mean the opposite -> true if it is <b>not</b> a prime and false otherwise.
     * That's due to the fact, that the default values after initializing an empty array are false and not true.
     *
     * @param dp the old sieve of eratosthenes array.
     * @return a new sieve of eratosthenes array doubled by the size.
     */
    private static boolean[] expandSieveEratosthenesArray(boolean[]dp) {
        int n = dp.length*2;
        boolean[] expanded = new boolean[n];
        for(int i = 0; i<dp.length; i++) {
            expanded[i] = dp[i];
            if(!dp[i]) {
                int j = dp.length-dp.length%i;
                if(j>=n) continue;
                while(j<dp.length || expanded[j]) {
                    j = j+i;
                    if(j>=n) break;
                }
                while(j<n) {
                    expanded[j] = true;
                    j = j+i;
                }
            }
        }
        return expanded;
    }

    public static void main(String[] args) {
        System.out.println(solution(3));
    }
}
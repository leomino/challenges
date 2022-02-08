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

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * Calculates the value of the shortest path from a number x to 1, where the only operations allowed are +1 -1 or /2.
 *
 * <p>I interpreted the problem as a graph-problem, where each node of the graph has either 1 or 2 children.
 * <br/>The node N has one child if it's value is an even number and two children if it's value is uneven:
 * <pre>
 *            10
 *            |
 *            5
 *           / \
 *          4   6
 *          |   |
 *          2   3
 *</pre>
 * Then performs an iterative BFS-search to get the shortest path from number x to 1.
 * Also, the class Path containing the size property is necessary to keep track of the Path's size.
 *
 * @since 2022-02-06
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first search</a>
 * @author Leonardo Palomino
 */
public class Solution_A {
    //the BigInteger.TWO const exists since Java 1.9 and wasn't available in Google's challenge-environment.
    //created the const for better readable code
    @Deprecated
    static final BigInteger TWO = BigInteger.valueOf(2);

    private static class Path {
        int size;
        BigInteger value;

        public Path(int size, BigInteger value) {
            this.size = size;
            this.value = value;
        }
    }

    /**
     * Performs the BFS algorithm to get the shortest path on the above-mentioned graph.
     *
     * @param x the number from where to start the path; 0 - 309 digit long.
     * @return the length of the shortest path from x to 1 using only +1, -1 and /2.
     * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first search</a>
     */
    public static int solution(String x) {
        HashMap<BigInteger, Boolean> visited = new HashMap<>();
        Queue<Path> q = new LinkedList<>();
        Path shortestPath = new Path(0, new BigInteger(x));
        q.offer(shortestPath);

        while (!q.isEmpty()) {
            Path curr = q.poll();
            if(curr.value.equals(ONE)) {
                shortestPath = curr;
                break;
            }
            if(visited.containsKey(curr.value)) continue;

            visited.put(curr.value, true);

            if (curr.value.mod(TWO).equals(ZERO)) {
                q.offer(new Path(curr.size+1, curr.value.divide(TWO)));
            } else {
                q.offer(new Path(curr.size+1, curr.value.subtract(ONE)));
                q.offer(new Path(curr.size+1, curr.value.add(ONE)));
            }
        }

        return shortestPath.size;
    }
}

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
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Given an int[][] matrix, where each m(i, j) represents the amount of boxes stacked on each other on that particular
 * place, get the total number of boxes necessary to add to the entire matrix so that every adjacent location
 * does not change in height by more than 1.
 *
 * @since 2022-02-18
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068cb14#problem">Rabbit House</a>
 * @author Leonardo Palomino
 */
public class RabbitHouse {

    /**
     * Class to store the coordinates as well as the initial box-count.
     * Implements comparable for the {@link PriorityQueue} storing the highest Vertex always at the top.
     */
    private static class Vertex implements Comparable<Vertex> {
        int r;
        int c;
        int val;
        public Vertex(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(o.val, this.val);
        }
    }

    /**
     * Returns the minimum amount of boxes necessary to turn the matrix values such that no adjacent cell-values,
     * differ in more than 1.
     * <p>Uses {@link PriorityQueue} to always get the Vertex with the highest box-count.
     * Using this highest vertex, accumulates all boxes necessary to align it´s adjacent vertices.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {

            int R = in.nextInt();
            int C = in.nextInt();
            PriorityQueue<Vertex> q = new PriorityQueue<>();

            int[][] m = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    m[r][c] = in.nextInt();
                    q.offer(new Vertex(m[r][c], r, c));
                }
            }

            long addedBoxes = 0;
            boolean[][] visited = new boolean[R][C];
            while (!q.isEmpty()) {
                Vertex curr = q.poll();
                if (visited[curr.r][curr.c] || m[curr.r][curr.c] != curr.val) continue;

                for (int[] adj : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                    int nR = curr.r + adj[0];
                    int nC = curr.c + adj[1];
                    if (nR < 0 || nR >= R || nC < 0 || nC >= C || visited[nR][nC]) continue;
                    if (m[nR][nC] < m[curr.r][curr.c] - 1) {
                        int diff = m[curr.r][curr.c] - 1 - m[nR][nC];
                        addedBoxes += diff;
                        m[nR][nC] += diff;
                        q.offer(new Vertex(m[nR][nC], nR, nC));
                    }
                }

                visited[curr.r][curr.c] = true;
            }

            System.out.println("Case #" + i + ": " + addedBoxes);
        }
    }
}

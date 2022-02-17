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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Counts all L-shapes within a matrix, where an L has one side which is at least double in size as the other side.
 * <p>First enqueues all found angles where an angle is a point in the matrix, where at least two sides from
 * different directions meet. Then goes from each angle to all four directions and counts all found L-shapes.
 *
 * @since 2022-02-17
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068c509">L-Shaped Plots</a>
 * @author Leonardo Palomino
 */
public class LShapedPlots {

    /**
     * Used to describe the edges from the matrix, where r represents the row- and c the column- index respectively.
     */
    private static class Vertex {
        int r;
        int c;

        public Vertex(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /**
     * Reads the input and transforms it into the matrix defined by R and C, representing the
     * expected row- and column- count respectively.
     * @param in the scanner to read the input stream.
     * @param R the expected row-count.
     * @param C the expected column-count.
     * @return the int[][] matrix.
     */
    private static int[][] getMatrix(Scanner in, int R, int C) {
        int[][] matrix = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                matrix[r][c] = in.nextInt();
            }
        }
        return matrix;
    }

    /**
     * Gets all angle-vertices from the given matrix, where an angle is a point in the matrix, where at least two sides from
     * different directions meet together.
     * @param matrix the matrix where to search for angle-vertices.
     * @param R the matrix's row-count
     * @param C the matrix's column-count
     * @return all found angle-points enqueued in a {@code LinkedList}
     */
    private static Queue<Vertex> getAnglesFrom(int[][] matrix, int R, int C) {
        LinkedList<Vertex> angles = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == 1) {
                    if ((0 <= r - 1 && matrix[r - 1][c] == 1) || (R > r + 1 && matrix[r + 1][c] == 1)) {
                        if ((0 <= c - 1 && matrix[r][c - 1] == 1) || (C > c + 1 && matrix[r][c + 1] == 1)) {
                            angles.offer(new Vertex(r, c));
                        }
                    }
                }
            }
        }
        return angles;
    }

    /**
     * Counts all L-shapes within a matrix, where an L has one side which is at least double in size as the other side.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int R = in.nextInt();
            int C = in.nextInt();
            int lShapes = 0;

            int[][] m = getMatrix(in, R, C);
            Queue<Vertex> angles = getAnglesFrom(m, R, C);

            //count l-shapes
            while (!angles.isEmpty()) {
                Vertex curr = angles.poll();
                int down = 0;
                for (int r = curr.r; r < R; r++) {
                    if (m[r][curr.c] != 1) break;
                    ++down;
                }
                int up = 0;
                for (int r = curr.r; r > -1; r--) {
                    if (m[r][curr.c] != 1) break;
                    ++up;
                }

                int right = 0;
                for (int c = curr.c; c < C; c++) {
                    if (m[curr.r][c] != 1) break;
                    ++right;
                    if (right > 1 && up >= right * 2) {
                        ++lShapes;
                    }
                    if (right > 1 && down >= right * 2) {
                        ++lShapes;
                    }
                }
                int left = 0;
                for (int c = curr.c; c > -1; c--) {
                    if (m[curr.r][c] != 1) break;
                    ++left;
                    if (left > 1 && up >= left * 2) {
                        ++lShapes;
                    }
                    if (left > 1 && down >= left * 2) {
                        ++lShapes;
                    }
                }

                up = 0;
                for (int r = curr.r; r < R; r++) {
                    if (m[r][curr.c] != 1) break;
                    ++up;
                    if (up > 1 && right >= up * 2) {
                        ++lShapes;
                    }
                    if (up > 1 && left >= up * 2) {
                        ++lShapes;
                    }
                }
                down = 0;
                for (int r = curr.r; r > -1; r--) {
                    if (m[r][curr.c] != 1) break;
                    ++down;
                    if (down > 1 && right >= down * 2) {
                        ++lShapes;
                    }
                    if (down > 1 && left >= down * 2) {
                        ++lShapes;
                    }
                }
            }

            System.out.println("Case #" + i + ": " + lShapes);
        }
    }
}

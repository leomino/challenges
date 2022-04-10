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

package palomino.leetcode.medium;

public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        int[] validate = new int[27];
        int box_c = 0;
        int box_r;
        for(int i = 0; i<9; i++) {
            box_r = i*3%9;
            if(i> 0 && i%3 == 0) ++box_c;

            for (int j = 0; j < 9; j++) {
                if(j>0 && j%3 == 0) ++box_r;

                char curr_r = board[i][j];
                char curr_c = board[j][i];
                char curr_b = board[box_r][j%3+box_c*3];

                if (curr_r != '.') {
                    if(validate[curr_r-'0'-1]==i+1) return false;
                    validate[curr_r - '0'-1] = i+1;
                }
                if (curr_c != '.') {
                    if(validate[curr_c-'0'+8]==i+1) return false;
                    validate[curr_c - '0'+8] = i+1;
                }
                if (curr_b != '.') {
                    if(validate[curr_b-'0'+17]==i+1) return false;
                    validate[curr_b - '0'+17] = i+1;
                }
            }
        }
        return true;
    }
}

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

import java.util.Arrays;

/**
 * Returns the sum of three distinct integers of a given array, closest to a target int.
 * @since 2022-04-06
 * @author Leonardo Palomino
 * @see <a href="https://leetcode.com/problems/3sum-closest/">3 Sum Closest</a>
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int min, max, currDiff, last = Integer.MAX_VALUE;
        Integer closest = null;
        for(int i = 0; i<nums.length; i++) {
            min = i+1;
            max =nums.length-1;

            while(min<max) {
                int curr = nums[i]+nums[min]+nums[max];
                currDiff = Math.max(curr, target)-Math.min(curr, target);
                if(curr>target) {
                    --max;
                } else if(curr<target) {
                    ++min;
                } else {
                    return target;
                }
                if(currDiff < last) {
                    closest = curr;
                    last = currDiff;
                }
            }
        }
        return closest;
    }
}

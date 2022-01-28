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
package palomino.foobar.level_2;

/**
 * Calculates the "gear radius" of a sequence of "peg locations".
 *
 * <p>The peg locations is a int[] of ascending Integers between 1 to 10.000, representing the locations from where the "gears" have to be placed.
 * A gear must touch but must not collide with the adjacent gear to it's right.
 * A gear with a size of at least 2 must be placed at each peg-location.
 * <p>The Problem: The first gear has to be exactly twice in size as the last gear.
 * <p>Example:
 * <pre>
 *        2x        x
 *     0----><-8-><--14
 *     Where x is the radius of the last gear.
 *     0, 8 and 14 represent the peg locations.
 * </pre>
 *
 * @since 2022-01-27
 * @author Leonardo Palomino
 */
public class Solution_B {

    /**
     * Calculates the last gear's radius and validates it to the constraints of each gear radius to be at least 1 and gears not to overlap.
     *
     * Based on the formula: lastGears radius = A-B+C-D+E-F...(+|-)N
     *
     * where A...N are the distances -> D(n) = pegs[n]-pegs[n-1]
     * <p>Note: when there is a even amount of points in pegs, the last Distance N always results to be added to the sum with a plus sign,
     * while otherwise it always results to be added with a minus sign.
     *
     * <p>If there was a even amount of points the last step of the formula, is to divide the sum by 3.
     *
     * @param pegs the peg's locations where to place a gear
     * @return the "axial rotation" of the first peg represented as a {@code int[]} of numerator and denominator of the first gear's radius.
     */
    public static int[] solution(int[] pegs) {
        boolean toggle = false;
        double lastRadius = 0;
        for(int i = 1; i<pegs.length; i++) {
            int distance = pegs[i]-pegs[i-1];
            lastRadius += toggle ? (distance)*-1 : distance;
            toggle = !toggle;
        }
        if(toggle) {
            lastRadius /= 3;
        }
        if(lastRadius < 1 || !validateGears(lastRadius, pegs)) {
            return new int[] { -1, -1 };
        }
        return convertDecimalToFraction(lastRadius*2);
    }

    /**
     * Validates if all gears are at least two in length (the radius must be greater or equal to 1).
     *
     * Based on the formula: gearRadius(x) = -2x+A-B+C-D...(+|-)N
     *
     * @param lastRadius the calculated last gear's radius
     * @param pegs the peg locations as int[]
     * @return true if all gears align the constraints, false otherwise.
     */
    public static boolean validateGears(double lastRadius, int[] pegs) {
        double nextGearRadius = -2*lastRadius;
        for(int i = 1; i<pegs.length; i++) {
            int distance = pegs[i]-pegs[i-1];
            nextGearRadius += distance;
            if(nextGearRadius < 1) return false;
            nextGearRadius*=-1;
        }
        return true;
    }

    /**
     * <a href="https://stackoverflow.com/questions/31585931/how-to-convert-decimal-to-fractions">copied code from stackoverflow</a>
     */
    public static int[] convertDecimalToFraction(double x) {
        double tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

        return new int[]{(int)h1, (int)k1};
    }
}

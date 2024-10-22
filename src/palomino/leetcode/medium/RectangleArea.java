package palomino.leetcode.medium;

public class RectangleArea {
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int h1 = ay2 - ay1;
        int b1 = ax2 - ax1;

        int h2 = by2 - by1;
        int b2 = bx2 - bx1;

        int A1 = h1 * b1;
        int A2 = h2 * b2;

        int result = A1 + A2;

        boolean outsideRight = bx1 >= ax2;
        boolean outsideLeft = bx2 <= ax1;
        boolean outsideTop = by1 >= ay2;
        boolean outsideBottom = by2 <= ay1;

        if (outsideTop || outsideBottom || outsideLeft || outsideRight) {
            return result;
        }

        boolean bInsideA = ax1 <= bx1 && ay1 <= by1 && ay2 >= by2 && ax2 >= bx2;
        if (bInsideA) {
            return A1;
        }
        boolean aInsideB = ax1 >= bx1 && ay1 >= by1 && ay2 <= by2 && ax2 <= bx2;
        if (aInsideB) {
            return A2;
        }

        int x = Math.max(by1, by2) - Math.min(ay1, ay2);
        int y = Math.max(ax2, ax1) - Math.min(bx1, bx2);

        int diff = x * y;

        return result - diff;
    }

    public static void main(String[] args) {
        System.out.println(computeArea(-2, -2, 2, 2, 1, 1, 3, 3));
    }
}

package palomino.codejam.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int max = 1000000;
            int[][] printers = new int[3][4];
            int[] constraints = new int[4];

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 4; c++) {
                    int next = in.nextInt();
                    constraints[c] = r == 0 ? next : Math.min(constraints[c], next);
                    printers[r][c] = next;
                }
            }

            if (Arrays.stream(constraints).sum() < max) {
                System.out.println("Case #" + i + ": IMPOSSIBLE");
                continue;
            }

            String[] res = new String[4];
            for (int j = 0; j < 4; j++) {
                if(max <= 0) {
                    res[j] = "0";
                } else {
                    res[j] = constraints[j] >= max ? max + "" : constraints[j] + "";
                    max-=constraints[j];
                }

            }

            System.out.println("Case #" + i + ": " + String.join(" ", res));
        }
    }
}

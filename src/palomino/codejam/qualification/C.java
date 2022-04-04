package palomino.codejam.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int[] dice = new int[N];
            for (int j = 0; j < N; j++) {
                dice[j] = in.nextInt();
            }
            Arrays.sort(dice);

            int count = 0;
            int last = 1;
            for (int j = 0; j < N; j++) {
                if (dice[j] >= last) {
                    ++count;
                    ++last;
                }
            }

            System.out.println("Case #" + i + ": " + count);
        }
    }
}

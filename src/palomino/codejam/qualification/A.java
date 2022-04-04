package palomino.codejam.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class A {
    /**
     *
     * int R;
     * int C;
     *
     * ..+-+-+
     * ..|.|.|
     * +-+-+-+
     * |.|.|.|
     * +-+-+-+
     *
     * ..+-+-+-+
     * ..|.|.|.|
     * +-+-+-+-+
     * |.|.|.|.|
     * +-+-+-+-+
     * |.|.|.|.|
     * +-+-+-+-+
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {

            int R = in.nextInt();
            int C = in.nextInt();
            String[][] result = new String[2*R+1][1];

            StringBuilder sb = new StringBuilder();

            String border = "-+";
            String row = ".|";

            String[] blocks = new String[]{border, row};
            String[] starting = new String[]{"+", "|"};

            for (int j = 0; j < R*2+1; j++) {
                if(j < 2) {
                    sb.append(".");
                } else {
                    sb.append(starting[j%2]);
                }
                for (int k = 0; k < C; k++) {
                    if(j == 0 && k == 0) {
                        sb.append(".+");
                    } else {
                        sb.append(blocks[j%2]);
                    }
                }
                result[j][0] = sb.toString();
                sb = new StringBuilder();
            }

            System.out.println("Case #" + i + ":");
            for (String[] strings : result) {
                System.out.println(strings[0]);
            }
        }
    }
}

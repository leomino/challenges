package palomino.codejam.archive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AppendSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();

            int result = 0;
            int last = in.nextInt();
            for (int j = 0; j < N-1; j++) {
                int curr = in.nextInt();
                if(curr>last) {
                    last = curr;
                } else {
                    String currString;
                    int prefix = Integer.parseInt(String.valueOf(last).substring(0, String.valueOf(curr).length()));
                    String suffix = String.valueOf(last).substring(String.valueOf(curr).length()-1);

                    if(curr==prefix) {
                        // append suffix increment by one
                        curr = Integer.parseInt(curr + String.valueOf(last).substring(String.valueOf(curr).length()))+1;

                        last = curr;
                    } else {
                        // append suffixes length+1 0's
                    }
                }
            }

            System.out.println("Case #" + i + ": " + result);
        }
    }
}

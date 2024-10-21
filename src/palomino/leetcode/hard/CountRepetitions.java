package palomino.leetcode.hard;


public class CountRepetitions {
    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int j = 0;
        int i = 0;

        while (true) {
            if (s1.charAt(i % s1.length()) == s2.charAt(j % s2.length())) {
                if (i > 0 && i % s1.length() == 0 && j > 0 && j % s2.length() == 0) {
                    break;
                }
                j++;
            }
            i++;
        }

        return i / s1.length() * n2;
    }

    public static void main(String[] args) {
        System.out.println(getMaxRepetitions("babb", 3, "baab", 1));
    }
}

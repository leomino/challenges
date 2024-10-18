package palomino.leetcode.medium;

public class MaximumNumberOfOperations {

    public static int solution(String relevantString) {
        int curr = 0;
        int res = 0;
        boolean hitFirstZero = false;
        for (int i = 0; i < relevantString.length(); i++) {
            if (relevantString.charAt(i) == '1') {
                curr++;
                hitFirstZero = false;
            } else if (!hitFirstZero) {
                res+=curr;
                hitFirstZero = true;
            }
        }
        return res;
    }

    static class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }
    public static int maxOperations(String binaryString) {
        int index = binaryString.lastIndexOf("10");
        if (index == -1) {
            return 0;
        }
        String relevantString = binaryString.substring(0, index + 1);

        int count = 0;
        int res = 0;
        Pair<Integer, Integer> prev;
        while (!relevantString.isEmpty()) {
            prev = helper(relevantString);
            count += prev.first;
            res += count;
            relevantString = relevantString.substring(prev.second);
        }
        return res;
    }

    private static Pair<Integer, Integer> helper(String s) {
        int i = s.indexOf("1");

        int currentBlockCount = 0;
        for (int j = i; j < s.length(); j++, i++) {
            if (s.charAt(j) == '1') {
                currentBlockCount++;
            } else {
                break;
            }
        }

        return new Pair<>(currentBlockCount, i);
    }

    public static void main(String[] args) {
        System.out.println(solution("1001101"));
    }
}

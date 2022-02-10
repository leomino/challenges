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

package palomino.foobar.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Solution_C {

    private static class Transition {
        Transition prev;
        State from;
        State to;
        double probability = 1d;
        double pathProbability = 1d;
        HashSet<Integer> relatedStates = new HashSet<>();

        public Transition(State initial, HashSet<Integer> relatedStates) {
            this.to = initial;
            this.relatedStates = relatedStates;
        }

        public Transition(Transition prev, HashSet<Integer> relatedStates, State from, State to, int[][] m) {
            this.prev = prev;
            this.from = from;
            this.relatedStates.addAll(relatedStates);
            this.to = to;
            this.probability = (double) m[from.index][to.index] / from.sum;
            this.pathProbability = prev.pathProbability * this.probability;
        }
    }

    private static class State {
        ArrayList<Integer> adjacentStates = new ArrayList<>();
        HashSet<Integer> relatedStates = new HashSet<>();
        int index;
        int sum;
        double staticProbability = 0;
        double variableProbability = 0;

        public State(int index) {
            this.index = index;
            this.sum = 0;
        }

        public double calculateProbability() {
            return (1 / (1 - this.variableProbability)) * this.staticProbability;
        }
    }

    public static int[] solution(int[][] m) {
        int n = m.length;
        HashMap<Integer, State> states = new HashMap<>();

        for (int i = 0; i < n; i++) {
            states.put(i, new State(i));
            for (int j = 0; j < n; j++) {
                if (m[i][j] > 0) {
                    states.get(i).sum += m[i][j];
                    states.get(i).adjacentStates.add(j);
                }
            }
        }

        HashMap<Integer, Double> repeatingStates = new HashMap<>();
        Queue<Transition> q = new LinkedList<>();
        q.offer(new Transition(states.get(0), new HashSet<>()));

        while (!q.isEmpty()) {
            Transition curr = q.poll();

            if (curr.to.adjacentStates.size() == 0) {
                states.get(curr.to.index).staticProbability += curr.pathProbability;
                states.get(curr.to.index).relatedStates = curr.relatedStates;
                continue;
            }

            if (curr.relatedStates.contains(curr.to.index)) {
                int repeatedIndex = curr.to.index;
                double prob = 1;

                do {
                    prob *= curr.probability;
                    curr = curr.prev;
                } while(curr != null && curr.to.index != repeatedIndex);

                if(repeatingStates.containsKey(repeatedIndex)) {
                    repeatingStates.put(repeatedIndex, repeatingStates.get(repeatedIndex)+prob);
                } else {
                    repeatingStates.put(repeatedIndex, prob);
                }
                continue;
            }
            curr.relatedStates.add(curr.to.index);

            for (Integer i : curr.to.adjacentStates) {
                q.offer(new Transition(curr, curr.relatedStates, curr.to, states.get(i), m));
            }
        }

        ArrayList<Double> probabilities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (states.get(i).adjacentStates.size() == 0) {
                State curr = states.get(i);
                for (Entry<Integer, Double> rep : repeatingStates.entrySet()) {
                    if (curr.relatedStates.contains(rep.getKey())) {
                        curr.variableProbability += rep.getValue();
                    }
                }
                probabilities.add(curr.calculateProbability());
            }
        }
        return getResult(probabilities);
    }

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

    private static int[] getResult(List<Double> probabilities) {
        List<int[]> fractions = probabilities.stream().map(Solution_C::convertDecimalToFraction).collect(Collectors.toList());
        int[] result = new int[probabilities.size()+1];

        int gcd = fractions.get(0)[1];
        for (int i = 0; i < fractions.size(); i++) {
            gcd = Math.max(fractions.get(i)[1], gcd);
        }

        final int finalGcd = gcd;
        Integer[] re = fractions.stream().map(f -> f[0]*=(finalGcd /f[1])).toArray(Integer[]::new);

        for (int i = 0; i < result.length-1; i++) {
            result[i] = re[i];
        }
        result[result.length-1] = gcd;

        return result;
    }

    public static void main(String[] args) {
        //standart beispiel
        System.out.println(Arrays.toString(solution(new int[][]{
                {0, 1, 0, 0, 0, 1},
                {4, 0, 0, 3, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        })));

        //kreis betrifft nicht alle punkte
        System.out.println(Arrays.toString(solution(new int[][]{
                {0, 1, 1, 0, 0},
                {0, 1, 0, 2, 0},
                {0, 0, 0, 0, 0},
                {0, 3, 0, 0, 1},
                {0, 0, 0, 0, 0}
        })));

        //wiederholung im selben punkt + ein punkt mit mehreren statischen pfaden
        System.out.println(Arrays.toString(solution(new int[][]{
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 0, 0},
        })));

        //große wiederholungs-schleife
        System.out.println(Arrays.toString(solution(new int[][]{
                {1, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        })));

        //nur ein punkt
        System.out.println(Arrays.toString(solution(new int[][]{
                {1, 1, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0}
        })));

        //stress test
        System.out.println(Arrays.toString(solution(new int[][]{
                {1, 1, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        })));
    }
}

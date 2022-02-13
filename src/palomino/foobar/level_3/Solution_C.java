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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution_C {

    private static class Edge {
        Edge prev;
        int to;
        double probability;
        public Edge(Edge prev, int to, double probability) {
            this.prev = prev;
            this.to = to;
            this.probability = probability;
        }
    }

    private static class State {
        LinkedList<Edge> terms;
        double probability = 1;
        public State() {
            this.terms = new LinkedList<>();
        }
    }

    public static int[] solution(int[][] m) {
        int n = m.length;
        double[][] probabilities = new double[n][n];
        boolean[] terminal = new boolean[n];
        HashMap<Integer, State> states = new HashMap<>();
        states.put(null, new State());

        for (int i = 0; i < n; i++) {
            states.put(i, new State());
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += m[i][j];
            }
            if (sum == 0) {
                terminal[i] = true;
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (m[i][j] > 0) {
                    probabilities[i][j] = (double) m[i][j] / sum;
                }
            }
        }

        //BFS in-order traversal
        LinkedList<Edge> traversal = new LinkedList<>();
        LinkedList<Integer> toBeCalculated = new LinkedList<>();
        Edge curr = new Edge(null, 0, 1);
        traversal.offer(curr);
        boolean[][] visited = new boolean[n][n];
        while (!traversal.isEmpty()) {
            curr = traversal.poll();
            if (curr.prev == null) {
                states.get(curr.to).terms.offer(curr);
            } else {
                if (visited[curr.prev.to][curr.to]) continue;
                states.get(curr.to).terms.offer(curr);
                visited[curr.prev.to][curr.to] = true;
            }
            if (!terminal[curr.to]) toBeCalculated.offer(curr.to);
            for (int j = 0; j < n; j++) {
                if (m[curr.to][j] > 0) {
                    traversal.offer(new Edge(curr, j, probabilities[curr.to][j]));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (terminal[i]) toBeCalculated.offer(i);
        }

        HashSet<Integer> resolved = new HashSet<>();
        State state;
        int stateId;
        while (!toBeCalculated.isEmpty()) {
            stateId = toBeCalculated.poll();
            if (!resolved.add(stateId)) continue;

            state = states.get(stateId);
            int counter = 0;
            double product = 0;
            double geometricSeries = 0;
            Edge edge;
            while (!state.terms.isEmpty()) {
                edge = state.terms.poll();
                if(counter++<1) {
                    product = states.get(edge.prev != null ? edge.prev.to : null).probability * edge.probability;
                    continue;
                }
                if(terminal[stateId]) {
                    product += states.get(edge.prev.to).probability * edge.probability;
                } else {
                    double pathProbability = 1;
                    double edgeProbability = edge.probability*states.get(edge.prev.to).probability;
                    while (edge.prev != null) {
                        pathProbability *= edge.probability;
                        if (edge.to != stateId && !terminal[edge.to] && states.get(edge.to).terms.size() > 1 && edge.prev.to != stateId) {
                            LinkedList<Edge> toBeMerged = states.get(edge.to).terms;
                            states.get(edge.to).terms = new LinkedList<>();
                            states.get(edge.to).terms.offer(toBeMerged.remove());
                            state.terms.addAll(toBeMerged);
                        }
                        if (edge.prev.to == stateId) {
                            geometricSeries += pathProbability;
                            break;
                        } else if(edge.prev.prev == null) {
                            product += edgeProbability;
                            break;
                        }
                        edge = edge.prev;
                    }
                }
            }
            state.probability = product * (1 / (1 - geometricSeries));
        }

        ArrayList<Double> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!terminal[i]) continue;
            p.add(states.get(i).probability);
        }
        return getResult(p);
    }

    public static int[] convertDecimalToFraction(double x) {
        double tolerance = 1.0E-6;
        double h1 = 1;
        double h2 = 0;
        double k1 = 0;
        double k2 = 1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1;
            h1 = a * h1 + h2;
            h2 = aux;
            aux = k1;
            k1 = a * k1 + k2;
            k2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(x - h1 / k1) > x * tolerance);

        return new int[]{(int) h1, (int) k1};
    }

    private static int[] getResult(List<Double> probabilities) {
        List<int[]> fractions = probabilities.stream().map(Solution_C::convertDecimalToFraction).collect(Collectors.toList());
        int[] result = new int[probabilities.size() + 1];

        int gcd = fractions.get(0)[1];
        for (int i = 0; i < fractions.size(); i++) {
            gcd = Math.max(fractions.get(i)[1], gcd);
        }

        final int finalGcd = gcd;
        Integer[] re = fractions.stream().map(f -> f[0] *= (finalGcd / f[1])).toArray(Integer[]::new);

        for (int i = 0; i < result.length - 1; i++) {
            result[i] = re[i];
        }
        result[result.length - 1] = gcd;

        return result;
    }
}

package org.example.solvers.day1;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;
import org.jetbrains.annotations.TestOnly;

import java.util.*;


public class Day1 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "1/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return getFileLocationPart1();
    }

    private List<Integer> getScores(InputHandle input) {
        List<Integer> scores = new ArrayList<>();
        scores.add(0);
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {//new person
                scores.add(0);
            } else {
                int lastScore = scores.get(scores.size() - 1);
                lastScore += Integer.parseInt(s);
                scores.set(scores.size() - 1, lastScore);
            }
        }
        return scores;
    }

    @Override
    public void solve1() {
        InputHandle input = generateIO(getFileLocationPart1());
        List<Integer> scores = getScores(input);
        int best = scores.stream().max(Integer::compare).get();
        System.out.println(best);
    }

    @Override
    public void solve2() {
        InputHandle input = generateIO(getFileLocationPart2());
        List<Integer> scores = getScores(input);
        Collections.sort(scores, Collections.reverseOrder());

        if (scores.size() < 3) {
            throw new RuntimeException("not enough people for top 3");
        }

        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += scores.get(i);
        }

        System.out.println(total);
    }
}

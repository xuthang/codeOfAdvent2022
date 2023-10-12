package org.example.solvers.day6;

import org.example.solvers.Solver;

import java.util.HashSet;
import java.util.Set;

public class Day6 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "6/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return getFileLocationPart1();
    }

    private int findFirstWindow(String s, int reqLen) {
        for (int i = reqLen - 1; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            Boolean foundCopy = false;
            for (int j = 0; j < reqLen; j++) {
                if (set.contains(s.charAt(i - j))) {
                    foundCopy = true;
                    break;
                } else {
                    set.add(s.charAt(i - j));
                }
            }
            if (!foundCopy) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        String s = input.getNextLine();

        int res = findFirstWindow(s, 4);
        System.out.println(res);
    }

    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart1());
        String s = input.getNextLine();

        int res = findFirstWindow(s, 14);
        System.out.println(res);
    }
}

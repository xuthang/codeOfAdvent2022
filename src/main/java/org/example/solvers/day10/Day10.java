package org.example.solvers.day10;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;
import org.example.solvers.day10.GridPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "10/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
//        return "10/sample.txt";
        return getFileLocationPart1();

        //EZFCHJAB
    }

    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        List<String> instructions = parseInput(input);

        int cycle = 0;
        int X = 1;
        int signalStrength = 0;
        List<Integer> interestingTimes = new ArrayList<>(Arrays.asList(20, 60, 100, 140, 180, 220));

        for (String ins : instructions) {
            int cycleCount = ins.split(" ")[0].equals("noop") ? 1 : 2;
            int diff = ins.split(" ")[0].equals("noop") ? 0 : Integer.parseInt(ins.split(" ")[1]);
            for (int i = 0; i < cycleCount; i++) {
                cycle++;
                if (interestingTimes.contains(cycle)) {
                    signalStrength += cycle * X;
                }
            }
            X += diff;
        }

        System.out.println(signalStrength);
    }

    private boolean shouldDraw(int cycle, int sprite) {
        return Math.abs(sprite - cycle%40) <= 1;
    }

    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart2());
        List<String> instructions = parseInput(input);
        List<Integer> draw = new ArrayList<>();
        int cycle = 0, spritePos = 1;

        for (String ins : instructions) {
            int cycleCount = ins.split(" ")[0].equals("noop") ? 1 : 2;
            int diff = ins.split(" ")[0].equals("noop") ? 0 : Integer.parseInt(ins.split(" ")[1]);
            for (int i = 0; i < cycleCount; i++) {
                if(shouldDraw(cycle, spritePos))
                    draw.add(cycle);
                cycle++;
            }
            spritePos += diff;
        }

        List<List<Character>> grid = new ArrayList<>();
        for(int i = 0; i < 6; i++)
            grid.add(new ArrayList<>(Collections.nCopies(40, '.')));

        for(int writeCycles : draw)
        {
            GridPos pos = GridPos.convert(writeCycles);
            grid.get(pos.i).set(pos.j, '#');
        }

        for(int i = 0; i < grid.size(); i++)
            System.out.println(grid.get(i).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining()));
    }

    private List<String> parseInput(InputHandle input) {
        List<String> ret = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty())
                break;
            ret.add(s);
        }
        return ret;

    }

}

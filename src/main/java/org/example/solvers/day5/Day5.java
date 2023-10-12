package org.example.solvers.day5;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day5 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "5/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return getFileLocationPart1();
    }

    void printSolution(Config config) {
        for (var tower : config.towers) {
            System.out.print(tower.getLast());
        }
        System.out.println();
    }

    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        Config config = parseInitConfig(input);
        String emptyline = input.getNextLine();
        List<OneMove> moves = parseMoves(input);

        for (var move : moves) {
            for (int i = 0; i < move.howMany; i++) {
                config.towers.get(move.to).addLast(config.towers.get(move.from).removeLast());
            }
        }
        printSolution(config);
    }

    private List<OneMove> parseMoves(InputHandle input) {
        List<OneMove> ret = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {
                break;
            }

            String[] tokens = s.split(" ");
            int howMany = Integer.parseInt(tokens[1]);
            int from = Integer.parseInt(tokens[3]);
            int to = Integer.parseInt(tokens[5]);
            ret.add(new OneMove(howMany, from - 1, to - 1));
        }

        return ret;
    }

    int getNumberOfTowers(String lastLine) {
        String[] numbers = lastLine.split(" ");
        return Integer.parseInt(numbers[numbers.length - 1]);
    }

    private Config parseInitConfig(InputHandle input) {
        List<String> readInput = new ArrayList<>();
        int numberOfTowers = 0;
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            //found the end of the initial config
            if (s.charAt(1) == '1') {
                numberOfTowers = getNumberOfTowers(s);
                break;
            }

            readInput.add(s);
        }

        List<LinkedList<Character>> config = new ArrayList<>();
        for (int i = 0; i < numberOfTowers; i++) {
            config.add(new LinkedList<>());
        }

        final String oneCell = "[A] ";
        for (String s : readInput) {
            // add to front of the stack if the i-th tower is not empty
            for (int i = 0; i * oneCell.length() < s.length() && i < config.size(); i++) {
                char box = s.charAt(oneCell.length() * i + 1);
                if (box != ' ') {
                    config.get(i).add(0, box);
                }
            }
        }

        return new Config(config);
    }

    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart1());
        Config config = parseInitConfig(input);
        String emptyline = input.getNextLine();
        List<OneMove> moves = parseMoves(input);

        for (var move : moves) {
            LinkedList<Character> tmp = new LinkedList<>();
            for (int i = 0; i < move.howMany; i++) {
                tmp.addFirst(config.towers.get(move.from).removeLast());
            }

            for (int i = 0; i < move.howMany; i++) {
                config.towers.get(move.to).addLast(tmp.removeFirst());
            }
        }

        printSolution(config);
    }
}

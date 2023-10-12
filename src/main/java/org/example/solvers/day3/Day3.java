package org.example.solvers.day3;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.ArrayList;
import java.util.List;

public class Day3 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "3/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return "3/input1.txt";
    }

    List<Compartment> parseInput(InputHandle input) {
        List<Compartment> list = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {
                break;
            }

            List<String> stringList = new ArrayList<>();
            stringList.add(s.substring(0, s.length() / 2));
            stringList.add(s.substring(s.length() / 2));

            list.add(new Compartment(stringList));
        }
        return list;
    }

    List<Compartment> parseInput2(InputHandle input) {
        List<Compartment> list = new ArrayList<>();

        List<String> stringList = new ArrayList<>();
        for (int i = 0; input.hasNextLine(); i++) {
            String s = input.getNextLine();

            if (s.isEmpty()) {
                break;
            }

            stringList.add(s);
            if (i % 3 == 2) {
                list.add(new Compartment(stringList));
                stringList = new ArrayList<>();
            }
        }

        return list;
    }


    @Override
    public void solve1() {
        InputHandle input = generateIO(getFileLocationPart1());
        List<Compartment> list = parseInput(input);
        int result = list.stream().map(Compartment::commonLetter).map(Converter::getItemPriority).reduce(0, Integer::sum);
        System.out.println(result);
    }

    @Override
    public void solve2() {
        InputHandle input = generateIO(getFileLocationPart2());
        List<Compartment> list = parseInput2(input);
        int result = list.stream().map(Compartment::commonLetter).map(Converter::getItemPriority).reduce(0, Integer::sum);
        System.out.println(result);
    }
}

package org.example.solvers.day4;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;
import org.example.solvers.day3.Compartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 implements Solver {

    @Override
    public String getFileLocationPart1() {
        return "4/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return getFileLocationPart1();
    }

    private List<Section> parseInput1(InputHandle input) {
        List<Section> ret = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {
                break;
            }
            String[] elves = s.split(",");

            String[] seats1 = elves[0].split("-");
            int p1 = Integer.parseInt(seats1[0]);
            int p2 = Integer.parseInt(seats1[1]);

            String[] seats2 = elves[1].split("-");
            int o1 = Integer.parseInt(seats2[0]);
            int o2 = Integer.parseInt(seats2[1]);

            ret.add(new Section(p1, p2, o1, o2));
        }

        return ret;
    }

    static Boolean containsEachOther(Section section) {
        if (section.p1 <= section.o1 && section.o2 <= section.p2)
            return true;

        if (section.p1 >= section.o1 && section.o2 >= section.p2)
            return true;

        return false;
    }

    static Boolean overlaps(Section section) {
        if (section.p1 <= section.o1 && section.o1 <= section.p2)
            return true;

        if (section.p1 <= section.o2 && section.o2 <= section.p2)
            return true;

        if (containsEachOther(section))
            return true;

        return false;
    }

    @Override
    public void solve1() {
        InputHandle input = generateIO(getFileLocationPart1());
        List<Section> list = parseInput1(input);
        int res = list.stream().map(Day4::containsEachOther).map(b -> b.compareTo(false)).reduce(0, Integer::sum);
        System.out.println(res);
    }

    @Override
    public void solve2() {
        InputHandle input = generateIO(getFileLocationPart2());
        List<Section> list = parseInput1(input);
        int res = list.stream().map(Day4::overlaps).map(b -> b.compareTo(false)).reduce(0, Integer::sum);
        System.out.println(res);
    }
}

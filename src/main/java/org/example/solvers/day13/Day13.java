package org.example.solvers.day13;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Day13 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "13/input1.txt";
    }

    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        List<Instance> instances = parseInput(input);

        int res = IntStream.range(0, instances.size())
                .filter(i -> instances.get(i).left.compareTo(instances.get(i).right) == Constants.RIGHT_ORDER)
                .map(i -> i + 1)
                .sum();

        System.out.println(res);
    }

    List<Character> convertToListChar(String s) {
        return s.chars().mapToObj(e -> (char) e).collect(toList());
    }

    private List<Instance> parseInput(InputHandle input) {
        List<Instance> ret = new ArrayList<>();
        while (input.hasNextLine()) {
            String s1 = input.getNextLine();
            String s2 = input.getNextLine();

            if (input.hasNextLine())
                input.getNextLine();//read and remove empty line

            Packet p1 = parseLine(convertToListChar(s1));
            Packet p2 = parseLine(convertToListChar(s2));

            ret.add(new Instance(p1, p2));
        }

        return ret;
    }

    OnePacket parseNumber(List<Character> line) {
        StringBuilder builder = new StringBuilder();
        while (Character.isDigit(line.get(0)))
            builder.append(line.remove(0));

        return new OnePacket(Integer.parseInt(builder.toString()));
    }


    private ListPacket parseLine(List<Character> line) {
        if (line.remove(0) != '[')
            throw new RuntimeException("expected an open bracket");

        List<Packet> ret = new ArrayList<>();
        while (true) {

            if (line.get(0) == ']') {
                line.remove(0);
                break;
            }

            if (line.get(0) == '[')
                ret.add(parseLine(line));
            else
                ret.add(parseNumber(line));

            if (line.get(0) == ',') {
                line.remove(0);
                assert (line.get(0) != ']');
            } else if (line.get(0) != ']')
                throw new RuntimeException("expected an close bracket or comma separator");
        }

        return new ListPacket(ret);
    }


    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart2());


        List<Instance> instances = parseInput(input);
        List<Packet> allInstances = instances.stream().flatMap(i -> Stream.of(i.left, i.right)).collect(toList());

        Packet p1 = parseLine(convertToListChar("[[2]]"));
        Packet p2 = parseLine(convertToListChar("[[6]]"));
        allInstances.add(p1);
        allInstances.add(p2);

        allInstances.sort(Packet::compareTo);

        int idx1 = allInstances.indexOf(p1) + 1;
        int idx2 = allInstances.indexOf(p2) + 1;

        int res = idx1 * idx2;
        System.out.println(res);
    }
}

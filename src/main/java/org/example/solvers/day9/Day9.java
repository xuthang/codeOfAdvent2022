package org.example.solvers.day9;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "9/input1.txt";
    }


    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        List<GridPos> moves = parseInput(input);
        Set<GridPos> visited = new HashSet<>();

        GridPos head = new GridPos(0, 0);
        GridPos tail = new GridPos(0, 0);
        for (GridPos move : moves) {
            head = head.add(move);
            tail = moveTail(head, tail);
            visited.add(tail);
        }

        System.out.println(visited.size());
    }

    private int normalize(int x) {
        return x == 0 ? x : x / Math.abs(x);
    }

    private GridPos moveTail(GridPos head, GridPos tail) {
        if (Math.abs(head.i - tail.i) <= 1 && Math.abs(head.j - tail.j) <= 1)
            return tail;

        return tail.add(new GridPos(normalize(head.i - tail.i), normalize(head.j - tail.j)));
    }

    private List<GridPos> parseInput(InputHandle input) {
        List<GridPos> ret = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty())
                break;

            String direction = s.split(" ")[0];
            int howMany = Integer.parseInt(s.split(" ")[1]);

            GridPos offset;

            if (direction.equals("U"))
                offset = new GridPos(0, +1);
            else if (direction.equals("D"))
                offset = new GridPos(0, -1);
            else if (direction.equals("L"))
                offset = new GridPos(-1, 0);
            else
                offset = new GridPos(+1, 0);

            for (int i = 0; i < howMany; i++)
                ret.add(offset);
        }

        return ret;
    }

    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart1());
        List<GridPos> moves = parseInput(input);
        Set<GridPos> visited = new HashSet<>();

        List<GridPos> snake = IntStream.range(0, 10).mapToObj(index -> new GridPos(0, 0)).collect(Collectors.toList());

        for (GridPos move : moves) {
            snake.set(0, snake.get(0).add(move));
            for(int i = 1; i < snake.size(); i++)
                snake.set(i, moveTail(snake.get(i-1), snake.get(i)));
            visited.add(snake.get(snake.size() -1));
        }

        System.out.println(visited.size());
    }
}

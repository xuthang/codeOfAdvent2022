package org.example.solvers.day12;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day12 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "12/input1.txt";
    }

    public class Instance {
        Grid grid;
        GridPosition start, end;

        public Instance(Grid grid, GridPosition start, GridPosition end) {
            this.grid = grid;
            this.start = start;
            this.end = end;
        }
    }

    void bfs(Map<GridPosition, Integer> D, Map<GridPosition, GridPosition> P, Instance instance) {
        Queue<GridPosition> q = new LinkedList<>();
        q.add(instance.end);
        D.put(instance.end, 0);
        P.put(instance.end, instance.end);

        while (!q.isEmpty()) {
            GridPosition cur = q.remove();
            for (GridPosition newPosition : generateNexts(cur, instance.grid)) {
                if (D.containsKey(newPosition))
                    continue;
                D.put(newPosition, D.get(cur) + 1);
                P.put(newPosition, cur);
                q.add(newPosition);
            }
        }
    }

    @Override
    public void solve1() {
        InputHandle input = generateIO(getFileLocationPart1());
        Instance instance = parseGrid(input);

        Map<GridPosition, Integer> D = new HashMap<>();
        Map<GridPosition, GridPosition> P = new HashMap<>();

        bfs(D, P, instance);

        System.out.println(D.get(instance.start));
    }

    private List<GridPosition> generateNexts(GridPosition cur, Grid grid) {
        List<GridPosition> ret = new ArrayList<>();

        ret.add(cur.add(new GridPosition(0, 1)));
        ret.add(cur.add(new GridPosition(0, -1)));
        ret.add(cur.add(new GridPosition(1, 0)));
        ret.add(cur.add(new GridPosition(-1, 0)));

        return ret.stream().filter(grid::isValidPosition)
                .filter(x -> grid.getPos(x) >= grid.getPos(cur) - 1)
                .collect(toList());
    }

    private Instance parseGrid(InputHandle input) {
        List<List<Integer>> grid = new ArrayList<>();
        GridPosition start = null, end = null;
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            List<Integer> line = s.chars().mapToObj(e -> (char) e).map(ch -> (int) ch).collect(Collectors.toList());
            grid.add(line);
            if (s.contains("S"))
                start = new GridPosition(grid.size() - 1, s.indexOf('S'));
            if (s.contains("E"))
                end = new GridPosition(grid.size() - 1, s.indexOf('E'));
        }

        Grid grid1 = new Grid(grid);
        grid1.setPos(start, 'a');
        grid1.setPos(end, 'z');

        return new Instance(grid1, start, end);
    }

    @Override
    public void solve2() {
        InputHandle input = generateIO(getFileLocationPart2());
        Instance instance = parseGrid(input);

        Map<GridPosition, Integer> D = new HashMap<>();
        Map<GridPosition, GridPosition> P = new HashMap<>();

        bfs(D, P, instance);

        var best = D.entrySet().stream()
                .filter(item -> instance.grid.getPos(item.getKey()) == 'a')
                .min(Comparator.comparingInt(Map.Entry::getValue));

        System.out.println(best.get().getValue());
    }
}

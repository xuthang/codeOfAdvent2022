package org.example.solvers.day8;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "8/input1.txt";
    }

    private boolean validPos(List<List<Integer>> grid, GridPos pos) {
        if (pos.i < 0 || pos.i >= grid.size())
            return false;

        return pos.j >= 0 && pos.j < grid.get(pos.i).size();
    }

    private List<GridPos> generateAllDirections() {
        return new ArrayList<>(Arrays.asList(
                new GridPos(0, 1),
                new GridPos(0, -1),
                new GridPos(1, 0),
                new GridPos(-1, 0)
        ));
    }


    private boolean isVisible(GridPos pos, List<List<Integer>> grid) {
        List<GridPos> offsets = generateAllDirections();
        int curHeight = grid.get(pos.i).get(pos.j);
        for (GridPos offset : offsets) {
            GridPos cur = pos.add(offset);
            boolean visible = true;
            while (validPos(grid, cur)) {
                if (grid.get(cur.i).get(cur.j) >= curHeight) {
                    visible = false;
                    break;
                }
                cur = cur.add(offset);
            }

            if (visible)
                return true;
        }
        return false;
    }

    @Override
    public void solve1() {
        InputHandle inputHandle = generateIO(getFileLocationPart1());
        List<List<Integer>> grid = parseInput(inputHandle);

        int cnt = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (isVisible(new GridPos(i, j), grid)) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private List<List<Integer>> parseInput(InputHandle inputHandle) {
        List<List<Integer>> ret = new ArrayList<>();
        while (inputHandle.hasNextLine()) {
            String s = inputHandle.getNextLine();
            if (s.isEmpty())
                break;
            ret.add(new ArrayList<>());
            for (char c : s.toCharArray()) {
                ret.get(ret.size() - 1).add(Character.getNumericValue(c));
            }
        }

        return ret;
    }

    @Override
    public void solve2() {
        InputHandle inputHandle = generateIO(getFileLocationPart1());
        List<List<Integer>> grid = parseInput(inputHandle);
        int best = -1;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                best = Integer.max(best, findScore(new GridPos(i, j), grid));
            }
        }

        System.out.println(best);
    }

    private int findScore(GridPos gridPos, List<List<Integer>> grid) {
        List<GridPos> offsets = generateAllDirections();
        int score = 1;
        for(GridPos offset: offsets){
            int prevHeight = grid.get(gridPos.i).get(gridPos.j);

            int cnt = 0;
            GridPos cur = gridPos.add(offset);

            while(validPos(grid, cur))
            {
                cnt++;
                if(grid.get(cur.i).get(cur.j) >= prevHeight)
                    break;
                cur = cur.add(offset);
            }
            score *= cnt;
        }

        return score;
    }
}

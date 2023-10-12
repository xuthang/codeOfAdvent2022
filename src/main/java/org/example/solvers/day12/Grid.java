package org.example.solvers.day12;

import java.util.List;

public class Grid {
    List<List<Integer>> map;

    public Grid(List<List<Integer>> map) {
        this.map = map;
    }

    int getPos(GridPosition pos) {

        return map.get(pos.i).get(pos.j);
    }

    void setPos(GridPosition pos, int val) {
        map.get(pos.i).set(pos.j, val);
    }

    boolean isValidPosition(GridPosition pos) {
        if (pos.i < 0 || pos.i >= map.size())
            return false;

        if (pos.j < 0 || pos.j >= map.get(pos.i).size())
            return false;

        return true;
    }
}

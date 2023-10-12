package org.example.solvers.day10;

public class GridPos {
    public int i;
    public int j;

    public GridPos(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static GridPos convert(int cycle) {
        return new GridPos(cycle / 40, cycle % 40);
    }
}

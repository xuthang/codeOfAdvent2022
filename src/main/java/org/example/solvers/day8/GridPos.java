package org.example.solvers.day8;

public class GridPos {
    public int i, j;

    public GridPos(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public GridPos add(GridPos other) {
        return new GridPos(i + other.i, j + other.j);
    }
}

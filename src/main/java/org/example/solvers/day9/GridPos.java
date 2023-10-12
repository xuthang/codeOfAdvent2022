package org.example.solvers.day9;

import java.util.Objects;

public class GridPos {
    public int i, j;

    public GridPos(int i, int j) {
        this.i = i;
        this.j = j;
    }


    public GridPos add(GridPos other) {
        return new GridPos(i + other.i, j + other.j);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPos gridPos = (GridPos) o;
        return i == gridPos.i && j == gridPos.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}

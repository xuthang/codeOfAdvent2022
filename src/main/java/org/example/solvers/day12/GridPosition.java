package org.example.solvers.day12;

import org.example.solvers.day9.GridPos;

import java.util.Objects;

public class GridPosition {
    int i, j;

    public GridPosition(int i, int j) {
        this.i = i;
        this.j = j;
    }


    GridPosition add(GridPosition other)
    {
        return new GridPosition(i + other.i, j + other.j);
    }

    @Override
    public String toString() {
        return "GridPosition{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPosition that = (GridPosition) o;
        return i == that.i && j == that.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}

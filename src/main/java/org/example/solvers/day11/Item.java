package org.example.solvers.day11;

import java.math.BigInteger;
import java.util.Objects;

public class Item {
    long worry;

    public Item(int worry) {
        this.worry = worry;
    }

    public Item(long worry) {
        this.worry = worry;
    }

    Item add(Item other) {
        return new Item(worry + other.worry);
    }

    Item subtract(Item other) {
        return new Item(worry - other.worry);
    }

    Item multiply(Item other) {
        return new Item(worry * other.worry);
    }

    Item divide(Item other) {
        return new Item(worry / other.worry);
    }

    Item mod(Item mod) {
        return new Item(worry % mod.worry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return worry == item.worry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(worry);
    }

    @Override
    public String toString() {
        return "Item{" +
                "worry=" + worry +
                '}';
    }
}

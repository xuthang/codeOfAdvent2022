package org.example.solvers.day5;

import java.util.LinkedList;
import java.util.List;

public class Config {
    List<LinkedList<Character>> towers;

    public Config(List<LinkedList<Character>> towers) {
        this.towers = towers;
    }

    @Override
    public String toString() {
        return "Config{" +
                "towers=" + towers +
                '}';
    }
}

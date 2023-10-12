package org.example.solvers.day3;

import java.util.*;

public class Compartment {
    List<String> strings;

    public Compartment(List<String> strings) {
        this.strings = strings;
    }

    public char commonLetter() {
        Map<Character, Integer> counter = new HashMap<>();
        for (String s : strings) {
            Set<Character> seen = new HashSet<>();
            for (char c : s.toCharArray()) {
                seen.add(c);
            }

            seen.forEach(c -> {
                if (counter.containsKey(c)) {
                    counter.put(c, counter.get(c) + 1);
                } else {
                    counter.put(c, 1);
                }
            });
        }

        for (var it : counter.entrySet()) {
            if (it.getValue() >= strings.size())
                return it.getKey();
        }

        throw new RuntimeException("compartment strings have nothing in common " + strings.toString());
    }
}

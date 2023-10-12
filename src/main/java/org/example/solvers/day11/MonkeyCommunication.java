package org.example.solvers.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonkeyCommunication {
    Map<Integer, Monkey> monkeys;

    public MonkeyCommunication(List<Monkey> monkeys) {
        this.monkeys = new HashMap<>();
        for (Monkey monkey : monkeys)
            this.monkeys.put(monkey.id, monkey);
    }

    public void throwToMonkey(int idThrower, int idCatcher, Item item) {
        monkeys.get(idCatcher).acceptItem(item);
    }
}

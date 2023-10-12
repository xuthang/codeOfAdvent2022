package org.example.solvers.day11;

import java.util.List;

public class Monkey {
    int id;
    List<Item> items;
    WorryCalculator operation;
    MonkeyTest test;

    long inspections = 0;

    public Monkey(int id, List<Item> items, WorryCalculator operation, MonkeyTest test) {
        this.id = id;
        this.items = items;
        this.operation = operation;
        this.test = test;
    }

    public void acceptItem(Item item) {
        items.add(item);
    }

    public void simulateMonkey(MonkeyCommunication communicator) {
        while (!items.isEmpty()) {
            Item item = items.remove(0);
            Item newItem = operation.apply(item);
            int newMonkey = test.testOperation(newItem);
            communicator.throwToMonkey(id, newMonkey, newItem);

            inspections++;
        }
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "id=" + id +
                ", items=" + items +
                ", inspections=" + inspections +
                '}';
    }
}

package org.example.solvers.day11;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.math.BigInteger;
import java.util.*;

public class Day11 implements Solver {

    @Override
    public String getFileLocationPart1() {
        return "11/input1.txt";
    }

    private BigInteger solve(List<Monkey> monkeys, int rounds) {
        MonkeyCommunication monkeyCommunication = new MonkeyCommunication(monkeys);

        for (int i = 1; i <= rounds; i++) {
            for (Monkey monkey : monkeys) {
                monkey.simulateMonkey(monkeyCommunication);
            }

//            System.out.println(i + " " + monkeys.stream()
//                    .map(monkey -> monkey.inspections)
//                    .toList());
        }


        return monkeys.stream()
                .map(monkey -> monkey.inspections)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .map(BigInteger::valueOf)
                .reduce(BigInteger.valueOf(1), BigInteger::multiply);
    }

    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        List<Monkey> monkeys = parseInput(input, x -> x.divide(new Item(3)));

        int rounds = 20;
        BigInteger res = solve(monkeys, rounds);
        System.out.println(res);
    }


    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart1());
        List<Monkey> monkeys = parseInput(input, x -> x);

        int rounds = 10000;
        BigInteger res = solve(monkeys, rounds);
        System.out.println(res);
    }


    private List<Item> getItems(List<String> input) {
        String monkeyItems = input.remove(0);
        StringTokenizer tokenizer = new StringTokenizer(monkeyItems, " ");

        String starting = tokenizer.nextToken();
        String items = tokenizer.nextToken();

        List<Item> ret = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String tmp = tokenizer.nextToken();
            ret.add(new Item(Integer.parseInt(tmp.split(",")[0])));
        }

        return ret;
    }

    private WorryCalculator parseOperation(List<String> input, WorryCalculator wrapper) {
        String monkeyOperation = input.remove(0);
        List<String> list = new ArrayList<>(Arrays.asList(monkeyOperation.split(" ")));
        final String op = list.get(6);
        final String m2 = list.get(7);

        return switch (op) {
            case "+" -> (x) -> wrapper.apply(
                    x.add(m2.equals("old") ? x : new Item(Integer.parseInt(m2)))
            );
            case "*" -> (x) -> wrapper.apply(
                    x.multiply(m2.equals("old") ? x : new Item(Integer.parseInt(m2)))
            );
            case "-" -> (x) -> wrapper.apply(
                    x.subtract(m2.equals("old") ? x : new Item(Integer.parseInt(m2)))
            );
            case "/" -> (x) -> wrapper.apply(
                    x.divide(m2.equals("old") ? x : new Item(Integer.parseInt(m2)))
            );
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }

    private MonkeyTest parseTest(List<String> input) {
        String testLine = input.remove(0);
        List<String> tmp = new ArrayList<>(Arrays.asList(testLine.split(" ")));
        final int mod = Integer.parseInt(tmp.get(tmp.size() - 1));

        String m1line = input.remove(0);
        List<String> m1Array = new ArrayList<>(Arrays.asList(m1line.split(" ")));
        final int idTrue = Integer.parseInt(m1Array.get(m1Array.size() - 1));

        String m2line = input.remove(0);
        List<String> m2Array = new ArrayList<>(Arrays.asList(m2line.split(" ")));
        final int idFalse = Integer.parseInt(m2Array.get(m2Array.size() - 1));

        return x -> x.mod(new Item(mod)).equals(new Item(0)) ? idTrue : idFalse;
    }

    private Monkey parseMonkey(List<String> input, WorryCalculator wrapper) {
        String monkeyId = input.remove(0);
        if (monkeyId.equals("")) return null;
        int id = Integer.parseInt(monkeyId.split(" ")[1].split(":")[0]);

        List<Item> items = getItems(input);
        WorryCalculator monkeyInspector = parseOperation(input, wrapper);
        MonkeyTest monkeyTest = parseTest(input);

        return new Monkey(id, items, monkeyInspector, monkeyTest);
    }


    private List<Monkey> parseInput(InputHandle input, WorryCalculator defaultInspector) {
        List<String> fileLines = new LinkedList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            fileLines.add(s);
        }

        WorryCalculator modulator = parseTotalModulo(new ArrayList<>(fileLines));
        WorryCalculator wrapper = x -> modulator.apply(defaultInspector.apply(x));

        List<Monkey> ret = new ArrayList<>();
        while (!fileLines.isEmpty()) {
            Monkey monkey = parseMonkey(fileLines, wrapper);

            if (monkey == null) break;
            else ret.add(monkey);

            if (!fileLines.isEmpty())
                fileLines.remove(0); //remove newline
        }

        return ret;
    }

    private WorryCalculator parseTotalModulo(List<String> lines) {
        int m = 1;
        for (String line : lines) {
            if (!line.contains("divisible"))
                continue;
            List<String> tmp = new ArrayList<>(Arrays.asList(line.split(" ")));
            m *= Integer.parseInt(tmp.get(tmp.size() - 1));
        }

        final int ret = m;
        return x -> x.mod(new Item(ret));
    }
}

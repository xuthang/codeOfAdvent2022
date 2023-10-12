package org.example.solvers.day2;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.ArrayList;
import java.util.List;

public class Day2 implements Solver {

    @Override
    public String getFileLocationPart1() {
        return "2/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return "2/input1.txt";
    }

    List<Round> getResponses(InputHandle input) {
        List<Round> responses = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {
                break;
            }
            responses.add(new Round(
                    new RPSChoice(Convertor.convertCharToRPS(s.charAt(0))),
                    new RPSChoice(Convertor.convertCharToRPS(s.charAt(2)))
            ));
        }
        return responses;
    }

    List<Round> getResponses2(InputHandle input) {
        List<Round> responses = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {
                break;
            }
            RPSChoice player1 = new RPSChoice(Convertor.convertCharToRPS(s.charAt(0)));
            RPSChoice player2 = new RPSChoice((Convertor.convertResToRPS(player1, s.charAt(2))));
            responses.add(new Round(player1, player2));
        }
        return responses;
    }


    @Override
    public void solve1() {
        InputHandle input = generateIO(getFileLocationPart1());
        List<Round> responses = getResponses(input);
        Integer sum = responses.stream().map(ScoreCalculator::getScore).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    @Override
    public void solve2() {
        InputHandle input = generateIO(getFileLocationPart2());
        List<Round> responses = getResponses2(input);
        Integer sum = responses.stream().map(ScoreCalculator::getScore).reduce(0, Integer::sum);
        System.out.println(sum);
    }
}

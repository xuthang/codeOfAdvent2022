package org.example.solvers.day2;

public interface Convertor {
    static RPS convertCharToRPS(char c) {
        switch (c) {
            case 'A':
                return RPS.ROCK;
            case 'B':
                return RPS.PAPER;
            case 'C':
                return RPS.SCISSOR;
        }

        switch (c) {
            case 'X':
                return RPS.ROCK;
            case 'Y':
                return RPS.PAPER;
            case 'Z':
                return RPS.SCISSOR;
        }

        throw new RuntimeException("found character " + c);
    }

    static RPS convertResToRPS(RPSChoice player1, char expected) {
        switch (expected) {
            case 'X':
                return player1.getWorse().getChoice();
            case 'Y':
                return player1.getChoice();
            case 'Z':
                return player1.getBetter().getChoice();
        }
        throw new RuntimeException("found character " + expected);
    }
}

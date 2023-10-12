package org.example.solvers.day2;

public interface ScoreCalculator {
    public static int getScore(RPSChoice choice) {
        switch (choice.getChoice()) {
            case ROCK -> {
                return 1;
            }
            case PAPER -> {
                return 2;
            }
            case SCISSOR -> {
                return 3;
            }
        }
        return 0;
    }

    public static int getRoundResult(RPSChoice player1, RPSChoice player2) {
        int result = player2.compareTo(player1);
        if (result == 1) {
            return 6;
        } else if (result == -1) {
            return 0;
        } else if (result == 0) {
            return 3;
        }
        return 0;
    }

    public static int getScore(Round round) {
        return getScore(round.player2) + getRoundResult(round.player1, round.player2);
    }
}

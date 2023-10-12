package org.example.solvers.day2;

public class RPSChoice implements Comparable<RPSChoice> {
    private RPS choice;

    public RPSChoice(RPS choice) {
        this.choice = choice;
    }

    public RPS getChoice() {
        return choice;
    }

    @Override
    public int compareTo(RPSChoice o) {
        if (getChoice() == o.getChoice()) return 0;
        return o.getChoice() == getWorse().getChoice()? 1 : -1;
    }

    public RPSChoice getBetter() {
        switch (this.choice) {
            case ROCK:
                return new RPSChoice(RPS.PAPER);
            case PAPER:
                return new RPSChoice(RPS.SCISSOR);
            default:
                return new RPSChoice(RPS.ROCK);
        }
    }

    public RPSChoice getWorse() {
        switch (this.choice) {
            case ROCK:
                return new RPSChoice(RPS.SCISSOR);
            case PAPER:
                return new RPSChoice(RPS.ROCK);
            default:
                return new RPSChoice(RPS.PAPER);
        }
    }

    @Override
    public String toString() {
        return choice.toString();
    }
}

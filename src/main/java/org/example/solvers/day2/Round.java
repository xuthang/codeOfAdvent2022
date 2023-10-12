package org.example.solvers.day2;

public class Round
{
    RPSChoice player1, player2;

    public Round(RPSChoice player1, RPSChoice player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return "Round{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }
}

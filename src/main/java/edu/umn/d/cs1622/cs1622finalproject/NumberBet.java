package edu.umn.d.cs1622.cs1622finalproject;

public class NumberBet extends RouletteBet{
    private int number;

    public NumberBet(int amt, int number) {
        super(amt);
        this.number = number;
        this.description = "Single Number: " + number;
    }

    @Override
    public boolean isWinner(int result) {
        return result == number;
    }

    @Override
    public int getPayoutMultiplier() {
        return 35; // 35 to 1 payout
    }
}

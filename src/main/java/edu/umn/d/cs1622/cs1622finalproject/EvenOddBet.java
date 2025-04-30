package edu.umn.d.cs1622.cs1622finalproject;

public class EvenOddBet extends RouletteBet {
    private boolean isEven;

    public EvenOddBet(int amt, boolean isEven) {
        super(amt);
        this.isEven = isEven;
        this.description = isEven ? "Even" : "Odd";
    }

    @Override
    public boolean isWinner(int result) {
        if (result == 0)
            return false;
        return (result % 2 == 0) == isEven;
    }

    @Override
    public int getPayoutMultiplier() {
        return 1; // 1 to 1 payout
    }
}

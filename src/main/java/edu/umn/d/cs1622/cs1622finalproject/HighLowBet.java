package edu.umn.d.cs1622.cs1622finalproject;

public class HighLowBet extends RouletteBet {
    private boolean isHigh;

    public HighLowBet(int amt, boolean isHigh) {
        super(amt);
        this.isHigh = isHigh;
        this.description = isHigh ? "High (19-36)" : "Low (1-18)";
    }

    @Override
    public boolean isWinner(int result) {
        if (result == 0)
            return false;
        return (result >= 19) == isHigh;
    }

    @Override
    public int getPayoutMultiplier() {
        return 1; // 1 to 1 payout
    }
}

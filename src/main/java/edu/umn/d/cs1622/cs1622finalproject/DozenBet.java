package edu.umn.d.cs1622.cs1622finalproject;

public class DozenBet extends RouletteBet {
    private int dozen;
    public DozenBet(int amt, int dozen) {
        super(amt);
        if (dozen < 1 || dozen > 3) {
            throw new IllegalArgumentException("Must be either 1, 2 or 3");
        }
        this.dozen = dozen;
        this.description = dozen + getDozenDescription(dozen);
    }

    private String getDozenDescription(int dozen) {
        return switch (dozen) {
            case 1 -> "st Dozen (1-12)";
            case 2 -> "nd Dozen (13-24)";
            case 3 -> "rd Dozen (25-36)";
            default -> "";
        };
    }

    @Override
    public boolean isWinner(int result) {
        if (result == 0)
            return false;

        if (dozen == 1) return result >= 1 && result <= 12;
        if (dozen == 2) return result >= 13 && result <= 24;
        if (dozen == 3) return result >= 25 && result <= 36;

        return false;
    }

    @Override
    public int getPayoutMultiplier() {
        return 2; // 2 to 1 payout
    }
}

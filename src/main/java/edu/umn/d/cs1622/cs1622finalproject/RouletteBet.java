package edu.umn.d.cs1622.cs1622finalproject;

public abstract class RouletteBet {
    public int amount;
    public String description;

    public RouletteBet(int amt) {
        this.amount = amt;
    }

    public abstract boolean isWinner(int result);

    public int calculateWinnings(int result) {
        if (isWinner(result)) {
            return amount + getPayoutMultiplier() * amount;
        }
        return 0;
    }

    public abstract int getPayoutMultiplier();

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

package edu.umn.d.cs1622.cs1622finalproject;

/**
 * An abstract class representing a bet in roulette.
 * This serves as a base class for all types of roulette bets.
 */

public abstract class RouletteBet {
    /** The amount of money bet */
    public int amount;

    /** A description of the bet */
    public String description;

    /**
     * Constructs a new roulette bet with the specified amount.
     *
     * @param amt The amount of money bet
     */
    public RouletteBet(int amt) {
        this.amount = amt;
    }

    /**
     * Determines if this bet is a winner based on the roulette result.
     *
     * @param result The number that came up on the roulette wheel
     * @return True if the bet wins, false otherwise
     */
    public abstract boolean isWinner(int result);

    /**
     * Calculates the total winnings for this bet.
     *
     * @param result The number that came up on the roulette wheel
     * @return The total winnings (original bet + payout) or 0 if the bet lost
     */
    public int calculateWinnings(int result) {
        if (isWinner(result)) {
            return amount + getPayoutMultiplier() * amount;
        }
        return 0;
    }

    /**
     * Gets the payout multiplier for this type of bet.
     *
     * @return The payout multiplier for this bet type
     */
    public abstract int getPayoutMultiplier();

    /**
     * Gets the amount of money bet.
     *
     * @return The bet amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets the description of this bet.
     *
     * @return A string description of the bet
     */
    public String getDescription() {
        return description;
    }
}

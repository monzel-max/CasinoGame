package edu.umn.d.cs1622.cs1622finalproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ColorBet extends RouletteBet{
    private boolean isRed;
    private static final Set<Integer> RED_NUMBERS =
            new HashSet<>(java.util.Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36));
    public ColorBet(int amt, boolean isRed) {
        super(amt);
        this.isRed = isRed;
        this.description = isRed ? "Red" : "Black";
    }

    @Override
    public boolean isWinner(int result) {
        if (result == 0)
            return false;
        boolean resultIsRed = RED_NUMBERS.contains(result);
        return resultIsRed == isRed;
    }

    @Override
    public int getPayoutMultiplier() {
        return 1; // 1 to 1 payout
    }
}

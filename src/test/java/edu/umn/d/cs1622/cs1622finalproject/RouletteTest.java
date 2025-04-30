package edu.umn.d.cs1622.cs1622finalproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteTest {
    @Test
    public void Roulette() {
        Player test = new Player(1000);
        int betAmount = 10;
        int winnings = betAmount * 35 + betAmount;
        test.addWinnings(winnings);

        assertEquals(1360, test.getBalance());

    }

}
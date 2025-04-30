package edu.umn.d.cs1622.cs1622finalproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasinoTest {
    @Test
    public void Casino() {
        Player test = new Player(1000);
        assertEquals(1000, test.getBalance());
    }

}
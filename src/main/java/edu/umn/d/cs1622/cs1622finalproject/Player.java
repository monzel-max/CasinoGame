package edu.umn.d.cs1622.cs1622finalproject;

public class Player {
    private int balance;

    public Player(int startingBalance) {
        this.balance = startingBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void addWinnings(int amt) {
        this.balance += amt;
    }

    public boolean placeBet(int amt) {
        if (amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }
}

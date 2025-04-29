package edu.umn.d.cs1622.cs1622finalproject;

public class Player {
    private String name;
    private int balance;

    public Player(String name, int startingBalance) {
        this.name = name;
        this.balance = startingBalance;
    }

    public String getName() {
        return name;
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

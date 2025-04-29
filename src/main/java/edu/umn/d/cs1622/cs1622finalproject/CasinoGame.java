package edu.umn.d.cs1622.cs1622finalproject;

import javafx.scene.Scene;

//An abstract game class to handle easily adding more games to the casino hub

public abstract class CasinoGame {
    public Player player;

    public CasinoGame(Player player) {
        this.player = player;
    }

    public abstract Scene gameScene();
}

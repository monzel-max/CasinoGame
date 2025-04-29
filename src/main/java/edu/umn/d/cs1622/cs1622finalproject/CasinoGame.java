package edu.umn.d.cs1622.cs1622finalproject;

import javafx.scene.Scene;

public abstract class CasinoGame {
    private Player player;

    public CasinoGame(Player player) {
        this.player = player;
    }
    public abstract Scene gameScene();
}

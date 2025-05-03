/*
 * CasinoGame.java
 */
package edu.umn.d.cs1622.cs1622finalproject;

import javafx.scene.Scene;

/**
 * An abstract class representing a casino game.
 * This serves as a base class for all games in the casino,
 * providing a consistent structure and interface.
 *
 */
public abstract class CasinoGame {
    /** The player participating in the game */
    public Player player;

    /**
     * Constructs a new casino game with the specified player.
     *
     * @param player The player who will be playing the game
     */
    public CasinoGame(Player player) {
        this.player = player;
    }

    /**
     * Creates and returns the main scene for the game.
     *
     * @return The JavaFX scene for this game
     */
    public abstract Scene gameScene();
}

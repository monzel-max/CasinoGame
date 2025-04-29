package edu.umn.d.cs1622.cs1622finalproject;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Roulette extends CasinoGame {
    private Random random = new Random();
    private Label resultLabel;
    private Label balanceLabel;
    private TextField betAmtField;
    private int selectedNumber = -1;
    private Circle wheel;

    public Roulette(Player player) {
        super(player);
    }

    @Override
    public Scene gameScene() {
        return null;
    }
}

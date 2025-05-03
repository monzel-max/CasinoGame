package edu.umn.d.cs1622.cs1622finalproject;

import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Random;

/**
 * Represents the Roulette game in the casino.
 * This class extends CasinoGame to implement the specific logic and UI for Roulette.
 */
public class Roulette extends CasinoGame {
    /** Random number generator for determining the roulette outcome */
    private Random random = new Random();

    /** Label displaying the result of the spin and bet */
    private Label resultLabel;

    /** Label displaying the player's current balance */
    private Label balanceLabel;

    /** Text field for entering the bet amount */
    private TextField betAmtField;

    /** The number selected by the player for betting */
    private int selectedNumber = -1;

    /** Circle shape representing the roulette wheel */
    private Circle wheel;

    /**
     * Constructs a new Roulette game with the specified player.
     *
     * @param player The player who will be playing the game
     */
    public Roulette(Player player) {
        super(player);
    }

    /**
     * Creates and returns the main scene for the Roulette game.
     *
     * @return The JavaFX scene for the Roulette game
     */
    @Override
    public Scene gameScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        //Top part of scene with title and balance
        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Roulette");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        balanceLabel = new Label("Balance: $" + player.getBalance());
        balanceLabel.setFont(Font.font("Arial", 16));

        resultLabel = new Label("Place your bet!");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        topBox.getChildren().addAll(titleLabel, balanceLabel, resultLabel);
        root.setTop(topBox);
        BorderPane.setMargin(topBox, new Insets(0, 0, 20, 0));

        // Creates the simple little wheel (idk how to put numbers on it)
        wheel = new Circle(200, 200, 150);
        wheel.setFill(Color.SADDLEBROWN);
        wheel.setStroke(Color.BLACK);
        wheel.setStrokeWidth(5);
        wheel.getStrokeDashArray().addAll(20.0, 10.0);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(wheel);
        root.setCenter(centerBox);

        // Right side of scene (betting grid)
        GridPane numberGrid = createBettingGrid();
        root.setRight(numberGrid);

        // Bottom side of scene (betting controls)
        VBox bottomControls = new VBox(15);
        bottomControls.setAlignment(Pos.CENTER);

        HBox betControls = new HBox(10);
        betControls.setAlignment(Pos.CENTER);

        Label betLabel = new Label("Bet Amount: $");
        betAmtField = new TextField("10");
        betAmtField.setPrefWidth(60);

        Button spinButton = new Button("Spin");
        spinButton.setOnAction(e -> spinWheel());

        betControls.getChildren().addAll(betLabel, betAmtField, spinButton);
        bottomControls.getChildren().add(betControls);

        root.setBottom(bottomControls);
        BorderPane.setMargin(bottomControls, new Insets(20, 0, 0, 0));


        return new Scene(root, 600, 700);
    }

    /**
     * Creates the grid of buttons representing the roulette betting area.
     *
     * @return A GridPane containing buttons for all numbers on the roulette wheel
     */
    private GridPane createBettingGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20));

        // Handles the green zero so that its separate from the grid 1-36 matrix
        final int zeroNumber = 0;
        Button zeroBtn = new Button(String.valueOf(zeroNumber));
        zeroBtn.setPrefWidth(160);
        zeroBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        zeroBtn.setOnAction(e -> {
            selectedNumber = zeroNumber;
            resultLabel.setText("Selected: " + zeroNumber);
        });

        GridPane.setColumnSpan(zeroBtn, 3);
        grid.add(zeroBtn, 0, 0);

        int col = 0;
        int row = 1;

        // Create buttons for numbers 1-36
        for (int i = 1; i <= 36; i++) {
            final int number = i;
            Button btn = new Button(String.valueOf(i));
            btn.setPrefWidth(50);

            // Simple red and black colors (not the actual board colors)
            if (i % 2 == 0) {
                btn.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            } else {
                btn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }

            btn.setOnAction(e -> {
                selectedNumber = number;
                resultLabel.setText("Selected: " + number);
            });
            grid.add(btn, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
        return grid;
    }

    /**
     * Handles the spinning of the roulette wheel when the player clicks the spin button.
     * Validates the bet, deducts it from the player's balance, and initiates the wheel animation.
     */
    private void spinWheel() {
        if (selectedNumber == -1) {
            resultLabel.setText("Please select a number first!");
            return;
        }

        try {
            int betAmount = Integer.parseInt(betAmtField.getText());

            if (betAmount <= 0) {
                resultLabel.setText("Bet amount must be positive!");
                return;
            }

            if (!player.placeBet(betAmount)) {
                resultLabel.setText("Insufficient funds!");
                return;
            }

            balanceLabel.setText("Balance: $" + player.getBalance());

            // Wheel spinner
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), wheel);
            rotateTransition.setByAngle(1080);
            rotateTransition.setCycleCount(1);
            rotateTransition.setOnFinished(event -> calculateWinLoss(betAmount));
            rotateTransition.play();
            resultLabel.setText("Spinning...");

            // Stops the code from bugging out when non-number is inputted
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid bet amount!");
        }
    }

    /**
     * Calculates and processes the result of the wheel spin.
     * Determines if the player won or lost and updates their balance accordingly.
     *
     * @param betAmount The amount that was bet
     */
    private void calculateWinLoss(int betAmount) {
        //Random number simulating 0-36 on the roulette table
        int result = random.nextInt(37);

        if (result == selectedNumber) {
            int winnings = betAmount * 35 + betAmount;
            player.addWinnings(winnings);
            resultLabel.setText("You won! Result: " + result + " - Winnings: $" + winnings);
        } else {
            resultLabel.setText("Sorry, you lost. Result: " + result);
        }

        balanceLabel.setText("Balance: $" + player.getBalance());
    }
}



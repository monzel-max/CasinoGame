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
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Roulette");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        balanceLabel = new Label("Balance: $" + player.getBalance());

        resultLabel = new Label("Place your bet!");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        topBox.getChildren().addAll(titleLabel, balanceLabel, resultLabel);
        root.setTop(topBox);
        BorderPane.setMargin(topBox, new Insets(0, 0, 20, 0));

        wheel = new Circle(100);
        wheel.setFill(Color.GREEN);
        wheel.setStroke(Color.BLACK);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(wheel);
        root.setCenter(centerBox);

        GridPane numberGrid = createBettingGrid();
        root.setRight(numberGrid);

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

    private GridPane createBettingGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20));

        final int zeroNumber = 0;
        Button zeroBtn = new Button(String.valueOf(zeroNumber));
        zeroBtn.setPrefWidth(160); // Make it span the width of 3 columns + gaps
        zeroBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        zeroBtn.setOnAction(e -> {
            selectedNumber = zeroNumber;
            resultLabel.setText("Selected: " + zeroNumber);
        });

        GridPane.setColumnSpan(zeroBtn, 3);
        grid.add(zeroBtn, 0, 0);

        int col = 0;
        int row = 1;

        // Create buttons for numbers 0-36
        for (int i = 1; i <= 36; i++) {
            final int number = i;
            Button btn = new Button(String.valueOf(i));
            btn.setPrefWidth(50);

            // Set button color based on roulette rules
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

    private void spinWheel() {
    }
}


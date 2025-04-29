package edu.umn.d.cs1622.cs1622finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class Casino extends Application {
    private Player player;
    private Stage mainStage;
    private Scene hub;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        //Starts the player off at the casino scene and with $1000
        this.mainStage = mainStage;
        this.player = new Player(1000);

        casinoHubScene();

        mainStage.setTitle("Casino");
        mainStage.setScene(hub);
        mainStage.show();
    }

    private void casinoHubScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        //Top section showing the title
        Label titleLabel = new Label("Casino");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(0, 0, 20, 0));

        // Middle section showing the selection of games
        VBox gameSelection = new VBox(15);
        gameSelection.setAlignment(Pos.CENTER);

        Label selectGameLabel = new Label("Select a game:");
        selectGameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        Button rouletteBtn = new Button("Roulette");
        rouletteBtn.setPrefWidth(200);
        rouletteBtn.setOnAction(e -> openRouletteGame());

        Button slotMachineBtn = new Button("Slot Machine");
        slotMachineBtn.setPrefWidth(200);

        Button blackjackBtn = new Button("Blackjack");
        blackjackBtn.setPrefWidth(200);

        gameSelection.getChildren().addAll(selectGameLabel, rouletteBtn, slotMachineBtn, blackjackBtn);
        root.setCenter(gameSelection);

        //Bottom section showing balance info
        HBox playerInfo = new HBox(15);
        playerInfo.setAlignment(Pos.CENTER);

        Label balanceLabel = new Label("Balance: $" + player.getBalance());

        playerInfo.getChildren().addAll(balanceLabel);
        root.setBottom(playerInfo);
        BorderPane.setMargin(playerInfo, new Insets(20, 0, 0, 0));

        hub = new Scene(root, 500, 400);
    }
    private void openRouletteGame() {
        Roulette roulette = new Roulette(player);
        Scene rouletteScene = roulette.gameScene();

        //Button at the bottom of the screen to get back to the main hub
        BorderPane root = (BorderPane) rouletteScene.getRoot();
        VBox bottomControls = (VBox) root.getBottom();

        Button backToHubBtn = new Button("Back to Hub");
        backToHubBtn.setOnAction(e -> {
            // Update player balance in hub
            casinoHubScene();
            mainStage.setScene(hub);
        });

        // Add the back button to the bottom controls
        bottomControls.getChildren().add(backToHubBtn);
        mainStage.setScene(rouletteScene);
        mainStage.setTitle("Roulette");
    }

}
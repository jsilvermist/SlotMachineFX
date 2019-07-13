package slotmachine;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SlotMachine extends Application {

  final int WINDOW_WIDTH = 640;
  final int WINDOW_HEIGHT = 480;

  Stage window;
  Scene welcomeScene, gameScene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Initialize window
    window = primaryStage;
    window.setTitle("Slot Machine");

    // Create and activate welcome scene
    welcomeScene = getWelcomeScene();
    window.setScene(welcomeScene);

    // Create game scene
    gameScene = getGameScene();

    // Show window
    window.show();
  }

  private Scene getWelcomeScene() {
    // Create layout container and centered vertical content box
    VBox layout = new VBox(20);
    layout.setAlignment(Pos.CENTER);

    // Create welcome label
    Label label = new Label("Welcome to my terrible...ly amazing slot machine!");

    // Create play button
    Button button = new Button("PLAY");
    button.setOnAction(event -> window.setScene(gameScene));

    // Append elements to content box
    layout.getChildren().addAll(label, button);

    return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  private Scene getGameScene() {
    // Create layout
    StackPane layout = new StackPane();

    // Create return button
    Button returnButton = new Button("Return to welcome screen");
    returnButton.setOnAction(event -> window.setScene(welcomeScene));

    // Append elements to content box
    layout.getChildren().add(returnButton);

    // StackPane stack = new StackPane();
    // stack.getChildren().addAll(new Rectangle(100,100,Color.BLUE), new Label("Go!"));

    return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

}

package slotmachine;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import slotmachine.component.ReelIcon;
import slotmachine.view.Payouts;

public class Main extends Application {

  public static final Payouts PAYOUTS = new Payouts();

  public final int WINDOW_WIDTH = 640;
  public final int WINDOW_HEIGHT = 480;

  public Stage window;
  public Scene welcomeScene, gameScene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Initialize window
    window = primaryStage;
    window.initStyle(StageStyle.DECORATED);
    window.setTitle("Slot Machine");
    window.setResizable(false);
    window.getIcons().add(ReelIcon.SEVEN.IMAGE);

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
    layout.getStylesheets().add("/slotmachine/view/style.css");

    // Create welcome logo, label, and play button
    ImageView logo = new ImageView(ReelIcon.SEVEN.IMAGE);
    logo.setFitWidth(200);
    logo.setFitHeight(200);
    Label label = new Label("Welcome to my terrible...ly amazing slot machine!");
    Button button = new Button("PLAY");
    button.setOnAction(event -> window.setScene(gameScene));

    // Append elements to content box
    layout.getChildren().addAll(logo, label, button);

    return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  private Scene getGameScene() throws IOException {
    Parent layout = FXMLLoader.load(getClass().getResource("view/slotmachine.fxml"));
    return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

}

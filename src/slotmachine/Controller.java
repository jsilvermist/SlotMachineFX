package slotmachine;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller implements Initializable {

  @FXML private Rectangle imageBlock1;
  @FXML private Rectangle imageBlock2;
  @FXML private Rectangle imageBlock3;

  @FXML private Text moneyText;
  @FXML private Text resultText;
  @FXML private HBox resultTextContainer;

  @FXML private Button bet1Button;
  @FXML private Button bet2Button;
  @FXML private Button bet5Button;

  private Image[] images = new Image[8];
  private int totalMoney = 100;
  private int betAmount = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateImages();
    populateText();
  }

  private void populateImages() {
    images[0] = new Image("/assets/bar.png");
    images[1] = new Image("/assets/cherry.png");
    images[2] = new Image("/assets/crown.png");
    images[3] = new Image("/assets/diamond.png");
    images[4] = new Image("/assets/lemon.png");
    images[5] = new Image("/assets/seven.png");
    images[6] = new Image("/assets/ten.png");
    images[7] = new Image("/assets/watermelon.png");
  }

  private void populateText() {
    resultTextContainer.setVisible(false);
    resultTextContainer.setManaged(false);
    moneyText.setText(Integer.toString(totalMoney));
  }

  private Image getRandomImage() {
    int selection = (int)(Math.random() * images.length);
    return images[selection];
  }

  private void spin() {
    imageBlock1.setFill(new ImagePattern(getRandomImage()));
    imageBlock2.setFill(new ImagePattern(getRandomImage()));
    imageBlock3.setFill(new ImagePattern(getRandomImage()));
  }

  public void userBet1() {
    betAmount = 1;
    bet();
  }

  public void userBet2() {
    betAmount = 2;
    bet();
  }

  public void userBet5() {
    betAmount = 5;
    bet();
  }

  private void bet() {
    totalMoney -= betAmount;
    spin();
    checkWin();
    checkMoney();
  }

  private void checkWin() {
    resultTextContainer.setVisible(true);
    resultTextContainer.setManaged(true);
    resultText.setText("You lost: $" + betAmount);

    moneyText.setText(Integer.toString(totalMoney));
  }

  private void checkMoney() {
    if (totalMoney < 5) {
      bet5Button.setDisable(true);
    } else {
      bet5Button.setDisable(false);
    }

    if (totalMoney < 2) {
      bet2Button.setDisable(true);
    } else {
      bet2Button.setDisable(false);
    }

    if (totalMoney < 1) {
      // Game over
      bet1Button.setDisable(true);
      resultText.setText("Game Over!");
    }
  }

}

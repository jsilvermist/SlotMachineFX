package slotmachine;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Controller implements Initializable {

  @FXML private ImageView imageBlock1;
  @FXML private ImageView imageBlock2;
  @FXML private ImageView imageBlock3;
  private ImageView[] imageBlocks = new ImageView[3];

  @FXML private Text moneyText;
  @FXML private Text resultText;
  @FXML private HBox resultTextContainer;

  @FXML private Button bet1Button;
  @FXML private Button bet2Button;
  @FXML private Button bet5Button;

  DecimalFormat df = new DecimalFormat("0.00");

  private Image[] images = new Image[8];
  private int[] selection = new int[3];
  private double totalMoney = 50.0;
  private int betAmount = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateImagesBlocks();
    populateImages();
    populateText();
  }

  private void populateImagesBlocks() {
    imageBlocks[0] = imageBlock1;
    imageBlocks[1] = imageBlock2;
    imageBlocks[2] = imageBlock3;
  }

  private void populateImages() {
    images[0] = new Image("/assets/lemon.png");
    images[1] = new Image("/assets/cherry.png");
    images[2] = new Image("/assets/watermelon.png");
    images[3] = new Image("/assets/diamond.png");
    images[4] = new Image("/assets/crown.png");
    images[5] = new Image("/assets/bar.png");
    images[6] = new Image("/assets/seven.png");
    images[7] = new Image("/assets/ten.png");
  }

  private void populateText() {
    resultTextContainer.setVisible(false);
    resultTextContainer.setManaged(false);
    moneyText.setText(df.format(totalMoney));
  }

  private void spin() {
    for (int i = 0; i < selection.length; i++) {
      selection[i] = (int)(Math.random() * images.length);
      imageBlocks[i].setImage(images[selection[i]]);
    }
  }

  private int getScoreWeight(int number) {
    switch (number) {
      case 0: return 15;
      case 1: return 20;
      case 2: return 25;
      case 3: return 40;
      case 4: return 50;
      case 5: return 75;
      case 6: return 100;
      case 7: return 150;
      default: return -1;
    }
  }

  private void checkWin() {
    resultTextContainer.setVisible(true);
    resultTextContainer.setManaged(true);

    int numbersMatched = -1;
    int matchedNumber = -1;

    if (selection[0] == selection[1] && selection[0] == selection[2]) {
      // Matched 3
      numbersMatched = 3;
      matchedNumber = selection[0];
    } else if (selection[0] == selection[1] || selection[0] == selection[2]) {
      // Matched 2
      numbersMatched = 2;
      matchedNumber = selection[0];
    } else if (selection[1] == selection[2]) {
      // Matched 2
      numbersMatched = 2;
      matchedNumber = selection[1];
    }

    double winnings = 0;

    if (numbersMatched == 3) {
      winnings = ((getScoreWeight(matchedNumber) / 10.0) * betAmount);
    } else if (numbersMatched == 2) {
      winnings = ((getScoreWeight(matchedNumber) / 30.0) * betAmount);
    } else {
      winnings = -betAmount;
    }

    if (winnings > 0) {
      resultText.setText("You won $" + df.format(Math.abs(winnings)) + ".");
    } else {
      resultText.setText("You lost $" + df.format(Math.abs(winnings)) + ".");
    }

    totalMoney += winnings;
    moneyText.setText(df.format(totalMoney));
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

  private void bet() {
    spin();
    checkWin();
    checkMoney();
  }

  public void handleBet1() {
    betAmount = 1;
    bet();
  }

  public void handleBet2() {
    betAmount = 2;
    bet();
  }

  public void handleBet5() {
    betAmount = 5;
    bet();
  }

}

package slotmachine;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Controller implements Initializable {

  @FXML private ImageView imageBlock1;
  @FXML private ImageView imageBlock2;
  @FXML private ImageView imageBlock3;
  private ImageView[] imageBlocks;

  @FXML private Text moneyText;
  @FXML private Text resultText;
  @FXML private HBox resultTextContainer;

  @FXML private Button bet1Button;
  @FXML private Button bet2Button;
  @FXML private Button bet5Button;

  private DecimalFormat df = new DecimalFormat("0.00");

  private Reel reel = new Reel();
  private double totalMoney = 50.0;
  private int betAmount = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initializeImagesBlocks();
    initializeText();
  }

  private void initializeImagesBlocks() {
    imageBlocks = new ImageView[] {
      imageBlock1,
      imageBlock2,
      imageBlock3
    };
  }

  private void updateImageBlocks() {
    ReelIcons[] icons = reel.getReelIcons();
    for (int i = 0; i < 3; i++) {
      imageBlocks[i].setImage(icons[i].IMAGE);
    }
  }

  private void initializeText() {
    resultTextContainer.setVisible(false);
    resultTextContainer.setManaged(false);
    moneyText.setText(df.format(totalMoney));
  }

  private void checkSpinResult() {
    double winnings = reel.getWinnings(betAmount);

    if (winnings > 0) {
      resultText.setText("You bet $" + betAmount + " and won $" + df.format(winnings) + ".");
      totalMoney += (winnings - betAmount);
    } else {
      resultText.setText("You bet $" + betAmount + " and lost.");
      totalMoney -= betAmount;
    }

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
      resultText.setText("Game over!");
    }
  }

  private void bet() {
    reel.spin();
    updateImageBlocks();
    checkSpinResult();
    checkMoney();

    resultTextContainer.setVisible(true);
    resultTextContainer.setManaged(true);
  }

  // Handle Events

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

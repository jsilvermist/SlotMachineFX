package slotmachine.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import slotmachine.Main;
import slotmachine.component.FileHandler;
import slotmachine.component.Reel;
import slotmachine.component.ReelIcon;

public class SlotMachine implements Initializable {

  @FXML private ImageView imageBlock1;
  @FXML private ImageView imageBlock2;
  @FXML private ImageView imageBlock3;
  private ImageView[] imageBlocks;

  @FXML private Text moneyText;
  @FXML private Text resultText;
  @FXML private Text highScoreText;
  @FXML private HBox resultTextContainer;
  @FXML private HBox buttonsContainer;
  @FXML private HBox playAgainContainer;

  @FXML private Button bet1Button;
  @FXML private Button bet2Button;
  @FXML private Button bet5Button;
  @FXML private Button playAgainButton;

  private final double INITIAL_MONEY = 50.0;

  private DecimalFormat df = new DecimalFormat("0.00");
  private FileHandler config = new FileHandler();
  private Reel reel = new Reel();
  private double totalMoney = INITIAL_MONEY;
  private double highScore = config.getProp("highscore", INITIAL_MONEY);
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
    ReelIcon[] icons = reel.getReelIcons();
    for (int i = 0; i < 3; i++) {
      imageBlocks[i].setImage(icons[i].IMAGE);
    }
  }

  private void resetImageBlocks() {
    for (int i = 0; i < 3; i++) {
      imageBlocks[i].setImage(ReelIcon.SEVEN.IMAGE);
    }
  }

  private void initializeText() {
    resultTextContainer.setVisible(false);
    resultTextContainer.setManaged(false);
    moneyText.setText(df.format(totalMoney));
    highScoreText.setText(df.format(highScore));
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
      buttonsContainer.setVisible(false);
      buttonsContainer.setManaged(false);
      playAgainContainer.setVisible(true);
      playAgainContainer.setManaged(true);
    } else {
      bet1Button.setDisable(false);
    }

    if (totalMoney > highScore) {
      highScore = totalMoney;
      highScoreText.setText(df.format(highScore));
      config.setProp("highscore", highScore);
    }
  }

  private void spinReel() {
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
    spinReel();
  }

  public void handleBet2() {
    betAmount = 2;
    spinReel();
  }

  public void handleBet5() {
    betAmount = 5;
    spinReel();
  }

  public void handlePlayAgain(ActionEvent event) {
    Node source = (Node)event.getSource();
    Node container = source.getParent();
    container.setVisible(false);
    container.setManaged(false);
    buttonsContainer.setVisible(true);
    buttonsContainer.setManaged(true);

    totalMoney = INITIAL_MONEY;
    initializeText();
    checkMoney();
    resetImageBlocks();
  }

  public void handleShowPayouts(ActionEvent event) {
    Main.PAYOUTS.show();
  }

}

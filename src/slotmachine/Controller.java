package slotmachine;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller implements Initializable {

  @FXML
  private Rectangle imageBlock1;

  @FXML
  private Rectangle imageBlock2;

  @FXML
  private Rectangle imageBlock3;

  @FXML
  private Text textBlock1;

  @FXML
  private Text textBlock2;

  private Image[] images = new Image[8];

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateImages();
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
    bet(1);
  }

  public void userBet2() {
    bet(2);
  }

  public void userBet5() {
    bet(5);
  }

  private void bet(int wager) {
    System.out.println("Betting $" + wager);
    textBlock2.setText(Integer.toString(wager));
    spin();
  }

}

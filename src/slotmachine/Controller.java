package slotmachine;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Controller implements Initializable {

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("View is now loaded!");
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

  }

}

package slotmachine.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import slotmachine.component.ReelIcon;

public class Payouts {

  private Stage window = new Stage();

  public Payouts() {
    try {
      Parent layout = FXMLLoader.load(getClass().getResource("payouts.fxml"));
      populateLayout(layout);
      Scene scene = new Scene(layout);
      window.setTitle("Payouts | Slot Machine");
      window.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void populateLayout(Parent layout) {
    GridPane grid = (GridPane)layout.lookup("#payoutsGrid");
    ReelIcon[] icons = ReelIcon.values();
    for (int i = 0; i < icons.length; i++) {
      Text matched2 = new Text((icons[i].SCORE / 40.0) + "x");
      grid.add(matched2, 1, i+1);
      Text matched3 = new Text((icons[i].SCORE / 10.0) + "x");
      grid.add(matched3, 2, i+1);
    }
  }

  public void show() {
    window.show();
    window.toFront();
  }

}
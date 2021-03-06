package slotmachine.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
      window.getIcons().add(ReelIcon.SEVEN.IMAGE);
      window.setResizable(false);
      window.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void populateLayout(Parent layout) {
    GridPane grid = (GridPane)layout.lookup("#payoutsGrid");
    ReelIcon[] icons = ReelIcon.values();

    for (int i = 0; i < icons.length; i++) {
      ImageView reelIcon = new ImageView(icons[i].IMAGE);
      reelIcon.setFitWidth(42);
      reelIcon.setFitHeight(42);

      Text matched2 = new Text(icons[i].getMatched2Multiplier() + "x");
      Text matched3 = new Text(icons[i].getMatched3Multiplier() + "x");

      grid.add(reelIcon, 0, i+1);
      grid.add(matched2, 1, i+1);
      grid.add(matched3, 2, i+1);
    }
  }

  public void show() {
    window.show();
    window.toFront();
  }

  public void close() {
    if (window.isShowing()) {
      window.close();
    }
  }

}

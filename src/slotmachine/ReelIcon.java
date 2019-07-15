package slotmachine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

public enum ReelIcon {

  LEMON("/assets/lemon.png", 10),
  CHERRY("/assets/cherry.png", 15),
  WATERMELON("/assets/watermelon.png", 20),
  DIAMOND("/assets/diamond.png", 30),
  CROWN("/assets/crown.png", 40),
  BAR("/assets/bar.png", 60),
  SEVEN("/assets/seven.png", 80),
  TEN("/assets/ten.png", 100);

  // Static

  private static final List<ReelIcon> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  public static ReelIcon getRandom()  {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }

  // Non-static

  public final int SCORE;
  public final Image IMAGE;

  ReelIcon(String assetPath, int scoreWeight) {
    SCORE = scoreWeight;
    IMAGE = new Image(assetPath);
  }

}

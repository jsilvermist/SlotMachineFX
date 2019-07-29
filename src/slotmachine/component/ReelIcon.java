package slotmachine.component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

public enum ReelIcon {

  LEMON("/assets/lemon.png", 20),
  CHERRY("/assets/cherry.png", 30),
  WATERMELON("/assets/watermelon.png", 40),
  DIAMOND("/assets/diamond.png", 60),
  CROWN("/assets/crown.png", 80),
  BAR("/assets/bar.png", 100),
  SEVEN("/assets/seven.png", 120),
  TEN("/assets/ten.png", 160);

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

  private ReelIcon(String assetPath, int scoreWeight) {
    SCORE = scoreWeight;
    IMAGE = new Image(assetPath);
  }

}

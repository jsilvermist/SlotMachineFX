package slotmachine.component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

public enum ReelIcon {

  LEMON("/assets/lemon.png", 30),
  CHERRY("/assets/cherry.png", 45),
  WATERMELON("/assets/watermelon.png", 60),
  DIAMOND("/assets/diamond.png", 90),
  CROWN("/assets/crown.png", 120),
  BAR("/assets/bar.png", 150),
  SEVEN("/assets/seven.png", 180),
  TEN("/assets/ten.png", 240);

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

  public double getMatched2Multiplier() {
    return SCORE / 60.0;
  }

  public double getMatched3Multiplier() {
    return SCORE / 10.0;
  }

}

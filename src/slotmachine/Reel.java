package slotmachine;

import java.util.Arrays;

public class Reel {

  private ReelIcon[] selectedIcons = new ReelIcon[3];

  Reel() {
    for (int i = 0; i < selectedIcons.length; i++) {
      selectedIcons[i] = ReelIcon.SEVEN;
    }
  }

  public void spin() {
    for (int i = 0; i < selectedIcons.length; i++) {
      selectedIcons[i] = ReelIcon.getRandom();
    }
  }

  public ReelIcon[] getReelIcons() {
    return Arrays.copyOf(selectedIcons, selectedIcons.length);
  }

  public double getWinnings(double betAmount) {
    ReelIcon matchedIcon = null;
    int numberOfMatchingIcons = 0;

    if (selectedIcons[0] == selectedIcons[1] && selectedIcons[0] == selectedIcons[2]) {
      matchedIcon = selectedIcons[0];
      numberOfMatchingIcons = 3;
    } else if (selectedIcons[0] == selectedIcons[1] || selectedIcons[0] == selectedIcons[2]) {
      matchedIcon = selectedIcons[0];
      numberOfMatchingIcons = 2;
    } else if (selectedIcons[1] == selectedIcons[2]) {
      matchedIcon = selectedIcons[1];
      numberOfMatchingIcons = 2;
    }

    double winnings = 0;

    if (numberOfMatchingIcons == 3) {
      winnings = (matchedIcon.SCORE / 10.0) * betAmount;
    } else if (numberOfMatchingIcons == 2) {
      winnings = (matchedIcon.SCORE / 30.0) * betAmount;
    }

    return winnings;
  }

}

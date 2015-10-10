package MineralClassification;

import java.util.HashMap;
import java.util.Map;

public class ResolvedMineralDatum implements ResolvedMineralDatumI {
  int red;
  int green;
  int blue;
  Map<String, Double> minerals = new HashMap<>();

  public ResolvedMineralDatum(int red, int green, int blue, Map<String, Double> minerals) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.minerals = minerals;
  }

  @Override public int getRed() {
    return red;
  }

  @Override public int getGreen() {
    return green;
  }

  @Override public int getBlue() {
    return blue;
  }

  @Override public Map<String, Double> getMinerals() {
    return new HashMap<>();
  }
}

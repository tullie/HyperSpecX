package MineralParsing;

import MineralParsing.MineralDatumI;
import java.util.HashMap;
import java.util.Map;

public class MineralDatum implements MineralDatumI {
  private Map<String, Double> minerals;
  private int red;
  private int green;
  private int blue;

  public MineralDatum(Map<String, Double> minerals, int red, int green, int blue) {
    this.minerals = minerals;
    this.red = red;
    this.green = green;
    this.blue = blue;
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
    return new HashMap<>(minerals);
  }
}

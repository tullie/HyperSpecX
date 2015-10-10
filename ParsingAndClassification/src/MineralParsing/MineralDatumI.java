package MineralParsing;

import java.util.Map;

public interface MineralDatumI {
  int getRed();
  int getGreen();
  int getBlue();
  Map<String, Double> getMinerals();
}

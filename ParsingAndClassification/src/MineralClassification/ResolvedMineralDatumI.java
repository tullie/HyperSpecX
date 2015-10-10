package MineralClassification;

import java.util.Map;

public interface ResolvedMineralDatumI {
  int getRed();
  int getGreen();
  int getBlue();
  Map<String, Double> getMinerals();
}

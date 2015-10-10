package MineralParsing.interfaces;

import java.util.Map;

public interface UnresolvedMineralDatumI {
  int getRed();
  int getGreen();
  int getBlue();
  Map<String, Double> getUnderReflectanceByMineral();
  Map<String, Double> getAboveReflectanceByMineral();
  boolean reflectanceSet();
}


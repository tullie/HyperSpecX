package MineralParsing;

import java.util.Map;

public interface UnresolvedMineralDatumI {
  int getRed();
  int getGreen();
  int getBlue();
  Map<String, Integer> getUnderReflectanceByMineral();
  Map<String, Integer> getAboveReflectanceByMineral();
  boolean reflectanceSet();
}


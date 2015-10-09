package MineralParsing;

import java.util.Map;

public interface MineralDatumI {
  RGBI getColor();
  Map<String, Integer> getUnderReflectanceByMineral();
  Map<String, Integer> getAboveReflectanceByMineral();
  boolean reflectanceSet();
}

package MineralParsing;

import java.util.List;
import java.util.Map;

public interface MineralGridI {
  List<List<MineralDatumI>> getMineralRect();
  void setMineralRect(List<List<MineralDatumI>> mineralRect);
  void setMineralsForDatum(int row, int col, Map<String, Double> mineralsForDatum);
}

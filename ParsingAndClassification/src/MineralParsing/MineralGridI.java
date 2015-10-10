package MineralParsing;

import java.util.List;

public interface MineralGridI {
  List<List<MineralDatumI>> getMineralRect();
  void setMineralRect(List<List<MineralDatumI>> mineralRect);
}

package MineralClassification;

import MineralParsing.MineralDatumI;
import MineralParsing.MineralGridI;

import java.util.List;

public class FloodFillClassifier implements MineralClassifierI {
  @Override public MineralGridI predictMineralData(MineralGridI rawMineralData) {
    List<List<MineralDatumI>> rect = rawMineralData.getMineralRect();

    return null;
  }
}

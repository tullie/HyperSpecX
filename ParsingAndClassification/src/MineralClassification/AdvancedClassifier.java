package MineralClassification;

import MineralParsing.MineralDatumI;
import MineralParsing.MineralGridI;

import java.util.ArrayList;
import java.util.List;

public class AdvancedClassifier implements  MineralClassifierI {
  @Override
  public MineralGridI predictMineralData(MineralGridI rawMineralData) {
    List<List<MineralDatumI>> resolvedMineralData = new ArrayList<>();

    for (List<MineralDatumI> row : rawMineralData.getMineralRect()) {
      for (MineralDatumI unresolvedDatum : row) {

      }
    }

    rawMineralData.setMineralRect(resolvedMineralData);
    return rawMineralData;
  }
}

package MineralClassification;

import MineralParsing.MineralDataI;

public interface MineralClassifierI {
  MineralDataI predictMineralData(MineralDataI rawMineralData);
}

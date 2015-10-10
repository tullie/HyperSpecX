package MineralClassification;

import MineralParsing.MineralGridI;

public interface MineralClassifierI {
  MineralGridI predictMineralData(MineralGridI rawMineralData);
}

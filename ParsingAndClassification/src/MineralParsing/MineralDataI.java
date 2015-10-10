package MineralParsing;

import MineralClassification.ResolvedMineralDatumI;

import java.util.List;

public interface MineralDataI {
  List<List<UnresolvedMineralDatumI>> getMineralRect();
  List<List<ResolvedMineralDatumI>> getResolvedMineralRect();
}

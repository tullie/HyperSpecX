package MineralParsing;

import MineralClassification.ResolvedMineralDatum;
import MineralClassification.ResolvedMineralDatumI;

import java.util.List;

public interface MineralDataI {
  List<List<UnresolvedMineralDatumI>> getUnresolvedMineralRect();
  List<List<ResolvedMineralDatumI>> getResolvedMineralRect();
  void setResolvedMineralRect(List<List<ResolvedMineralDatumI>> resolvedMineralRect);

}

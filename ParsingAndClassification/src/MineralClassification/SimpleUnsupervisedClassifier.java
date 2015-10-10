package MineralClassification;

import MineralParsing.MineralDataI;
import MineralParsing.UnresolvedMineralDatumI;

import java.util.List;

/**
 * Extrapolate pixel spectral reflectance based of nearby known reflectance data.
 */
public class SimpleUnsupervisedClassifier implements MineralClassifierI {

  @Override
  public MineralDataI predictMineralData(MineralDataI rawMineralData) {
    return new MineralDataI() {
      @Override public List<List<UnresolvedMineralDatumI>> getMineralRect() {
        return null;
      }

      @Override public List<List<ResolvedMineralDatumI>> getResolvedMineralRect() {
        return null;
      }
    };
  }
}

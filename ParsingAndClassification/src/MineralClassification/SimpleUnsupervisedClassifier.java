package MineralClassification;

import MineralParsing.MineralDataI;
import MineralParsing.UnresolvedMineralDatumI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extrapolate pixel spectral reflectance based of nearby known reflectance data.
 */
public class SimpleUnsupervisedClassifier implements MineralClassifierI {

  @Override
  public MineralDataI predictMineralData(MineralDataI rawMineralData) {
    List<List<ResolvedMineralDatumI>> resolvedMineralData = new ArrayList<>();

    for (List<UnresolvedMineralDatumI> row : rawMineralData.getUnresolvedMineralRect()) {

      // Find spectral average.
      List<Map<String, Double>> spectralMinerals = new ArrayList<>();
      for (UnresolvedMineralDatumI unresolvedDatum : row) {
        if (unresolvedDatum.reflectanceSet()) {
          spectralMinerals.add(unresolvedDatum.getAboveReflectanceByMineral());
        }
      }
      Map<String, Double> averageMinerals = averageMinerals(spectralMinerals);

      // Set resolve row to the average.
      List<ResolvedMineralDatumI> resolvedRow = new ArrayList<>();
      for (UnresolvedMineralDatumI unresolvedDatum : row) {
        resolvedRow.add(new ResolvedMineralDatum(unresolvedDatum.getRed(),
                                                 unresolvedDatum.getGreen(),
                                                 unresolvedDatum.getBlue(),
                                                 averageMinerals));
      }
      resolvedMineralData.add(resolvedRow);
    }

    rawMineralData.setResolvedMineralRect(resolvedMineralData);
    return rawMineralData;
  }

  private Map<String,Double> averageMinerals(List<Map<String, Double>> mineralsPerPixel) {
    Map<String, Double> averageMinerals = new HashMap<>();

    for (Map<String, Double> minerals : mineralsPerPixel) {
      for (Map.Entry<String, Double> mineralEntry : minerals.entrySet()) {
        double average = 0;
        if (averageMinerals.containsKey(mineralEntry.getKey())) {
          average = averageMinerals.get(mineralEntry.getKey());
        }

        double newAverage = (average + mineralEntry.getValue()) / 2.0;
        averageMinerals.put(mineralEntry.getKey(), newAverage);
      }
    }
    return averageMinerals;
  }
}

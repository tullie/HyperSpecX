package MineralClassification;

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
import MineralParsing.MineralDataI;
import MineralParsing.UnresolvedMineralDatumI;
=======
import MineralParsing.interfaces.MineralDataI;
import MineralParsing.interfaces.ResolvedMineralDatumI;
import MineralParsing.interfaces.UnresolvedMineralDatumI;
>>>>>>> pushing

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

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
  public Map<String,Double> averageMinerals(List<Map<String, Double>> mineralsPerPixel) {
    Map<String, Double> averageMinerals = new HashMap<>();
    Map<String, Integer> mineralCounts = new HashMap<>();

    for (Map<String, Double> minerals : mineralsPerPixel) {
      for (Map.Entry<String, Double> mineralEntry : minerals.entrySet()) {
        int count = 0;
        if (mineralCounts.containsKey(mineralEntry.getKey())) {
          count = mineralCounts.get(mineralEntry.getKey());
        }

=======
  private Map<String,Double> averageMinerals(List<Map<String, Double>> mineralsPerPixel) {
    Map<String, Double> averageMinerals = new HashMap<>();

    for (Map<String, Double> minerals : mineralsPerPixel) {
      for (Map.Entry<String, Double> mineralEntry : minerals.entrySet()) {
>>>>>>> pushing
        double average = 0;
        if (averageMinerals.containsKey(mineralEntry.getKey())) {
          average = averageMinerals.get(mineralEntry.getKey());
        }

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
        double newAverage = (average * count + mineralEntry.getValue()) / (count + 1);
        averageMinerals.put(mineralEntry.getKey(), newAverage);
        mineralCounts.put(mineralEntry.getKey(), count + 1);
      }
    }

=======
        double newAverage = (average + mineralEntry.getValue()) / 2.0;
        averageMinerals.put(mineralEntry.getKey(), newAverage);
      }
    }
>>>>>>> pushing
    return averageMinerals;
  }
}

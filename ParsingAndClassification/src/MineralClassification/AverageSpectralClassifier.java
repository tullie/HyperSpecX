package MineralClassification;

import MineralParsing.MineralDatum;
import MineralParsing.MineralDatumI;
import MineralParsing.MineralGridI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extrapolate pixel spectral reflectance based of nearby known reflectance data.
 */
public class AverageSpectralClassifier implements MineralClassifierI {

  @Override
  public MineralGridI predictMineralData(MineralGridI rawMineralData) {
    List<List<MineralDatumI>> resolvedMineralData = new ArrayList<>();

    for (List<MineralDatumI> row : rawMineralData.getMineralRect()) {

      // Find spectral average.
      List<Map<String, Double>> spectralMinerals = new ArrayList<>();
      for (MineralDatumI unresolvedDatum : row) {
        if (unresolvedDatum.getMinerals() != null) {
          spectralMinerals.add(unresolvedDatum.getMinerals());
        }
      }
      Map<String, Double> averageMinerals = averageMinerals(spectralMinerals);

      // Set resolve row to the average.
      List<MineralDatumI> resolvedRow = new ArrayList<>();
      for (MineralDatumI unresolvedDatum : row) {
        resolvedRow.add(new MineralDatum(averageMinerals,
                                         unresolvedDatum.getRed(),
                                         unresolvedDatum.getGreen(),
                                         unresolvedDatum.getBlue() ));
      }
      resolvedMineralData.add(resolvedRow);
    }

    rawMineralData.setMineralRect(resolvedMineralData);
    return rawMineralData;
  }

  public Map<String,Double> averageMinerals(List<Map<String, Double>> mineralsPerPixel) {
    Map<String, Double> averageMinerals = new HashMap<>();
    Map<String, Integer> mineralCounts = new HashMap<>();

    for (Map<String, Double> minerals : mineralsPerPixel) {
      for (Map.Entry<String, Double> mineralEntry : minerals.entrySet()) {
        int count = 0;
        if (mineralCounts.containsKey(mineralEntry.getKey())) {
          count = mineralCounts.get(mineralEntry.getKey());
        }

        double average = 0;
        if (averageMinerals.containsKey(mineralEntry.getKey())) {
          average = averageMinerals.get(mineralEntry.getKey());
        }

        double newAverage = (average * count + mineralEntry.getValue()) / (count + 1);
        averageMinerals.put(mineralEntry.getKey(), newAverage);
        mineralCounts.put(mineralEntry.getKey(), count + 1);
      }
    }
    return averageMinerals;
  }
}

package MineralClassification;

import MineralParsing.MineralDatumI;
import MineralParsing.MineralGridI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Converts MineralGrid to JSON strings.
 */
public class MineralDataToJson {
  public static String parseResolvedMineralData(MineralGridI mineralGridI) {
   return parseResolvedMineralDataToJson(mineralGridI).toString();
  }

  private static JSONArray parseResolvedMineralDataToJson(MineralGridI mineralData) {
    JSONArray outerArray = new JSONArray();
    for (List<MineralDatumI> row : mineralData.getMineralRect()) {
      for (MineralDatumI datum : row) {
        if (datum.getMinerals() != null) {
          outerArray.add(parseResolvedMineralDatumToJson(datum));
        }
      }
    }

    return outerArray;
  }

  private static JSONObject parseResolvedMineralDatumToJson(MineralDatumI mineralDatum) {
    JSONObject jsonDatum = new JSONObject();
    jsonDatum.put("r", mineralDatum.getRed());
    jsonDatum.put("g", mineralDatum.getGreen());
    jsonDatum.put("b", mineralDatum.getBlue());

    JSONObject jsonMinerals = new JSONObject();
    for (Map.Entry<String, Double> mineralEntry : mineralDatum.getMinerals().entrySet()) {
      jsonMinerals.put(mineralEntry.getKey(), mineralEntry.getValue());
    }
    jsonDatum.put("m", jsonMinerals);

    return jsonDatum;
  }
}

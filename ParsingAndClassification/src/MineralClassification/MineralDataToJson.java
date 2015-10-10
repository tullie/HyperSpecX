package MineralClassification;

import MineralParsing.MineralDataI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Converts MineralData to JSON strings.
 */
public class MineralDataToJson {
  public static String parseResolvedMineralData(MineralDataI mineralDataI) {
   return parseResolvedMineralDataToJson(mineralDataI).toString();
  }

  private static JSONArray parseResolvedMineralDataToJson(MineralDataI mineralData) {
    JSONArray outerArray = new JSONArray();
    for (List<ResolvedMineralDatumI> row : mineralData.getResolvedMineralRect()) {
      for (ResolvedMineralDatumI datum : row) {
        outerArray.add(parseResolvedMineralDatumToJson(datum));
      }
    }

    return outerArray;
  }

  private static JSONObject parseResolvedMineralDatumToJson(ResolvedMineralDatumI mineralDatum) {
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

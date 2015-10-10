package MineralClassification;

import java.util.HashMap;
import java.util.Map;

public class MineralUtil {
  static Map<String, Integer> idsForMinerals = new HashMap<>();
  static String[] minerals =
      {"Kaolinite WX", "Kaolinite PX", "Dickite", "Muscovite", "Paragonite", "Montmorillonite",
          "Nontronite", "Magnesium Clays", "FeMgChlorite", "MgChlorite", "Biotite", "Phlogopite",
          "Actinolite", "Riebeckite", "Hornblende", "Serpentine", "Epidote", "Zoisite",
          "FeTourmaline", "Calcite", "Dolomite", "Ankerite", "Magnesite", "Siderite", "Jarosite",
          "Opal", "Illitic Muscovite", "Fe2+ Goethite", "Fe3+ Goethite", "FeChlorite", "Talc",
          "Rubellite"};

  static {
    for (int i = 0; i < minerals.length; ++i) {
      idsForMinerals.put(minerals[i], i);
    }
  }

  static double mineralToId(String mineral) {
    if (!idsForMinerals.containsKey(mineral)) {
      return 0.0;
    }
    return idsForMinerals.get(mineral);
  }

  static String[] getMinerals() {
   return minerals;
  }

  static int mineralCount() {
    return minerals.length;
  }

  public static String mineralFromId(int i) {
    return minerals[i];
  }
}

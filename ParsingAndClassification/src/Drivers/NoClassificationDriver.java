package Drivers;

import MineralClassification.MineralDataToJson;
import MineralClassification.ColorMemory;
import MineralParsing.MineralGridI;
import MineralParsing.Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NoClassificationDriver {
  public static void main(String[] arg) throws FileNotFoundException {

    int height = 1500;

    // Parse files.
    Parse parse = new Parse();
    MineralGridI unresolvedMineralGrid = parse.parseFilesToMineralData(height, 124, 2);

    ColorMemory result = new ColorMemory(unresolvedMineralGrid);

    // Parse classified minerals to JSON.
    String json = MineralDataToJson.parseResolvedMineralData(result.getGrid(height));
    PrintWriter out = null;
    try {
      out = new PrintWriter(new File("resources/chunk-0.json"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    out.write(json);
    out.close();
  }
}

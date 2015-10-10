package Drivers;

import MineralClassification.MineralDataToJson;
import MineralParsing.MineralGridI;
import MineralParsing.Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NoClassificationDriver {
  public static void main(String[] arg) throws FileNotFoundException {

    // Parse files.
    Parse parse = new Parse();
    MineralGridI unresolvedMineralGrid = parse.parseFilesToMineralData(1000, 124);

    // Parse classified minerals to JSON.
    String json = MineralDataToJson.parseResolvedMineralData(unresolvedMineralGrid);

    // Write JSON to file.
    PrintWriter out = new PrintWriter(new File("picture.json"));
    out.write(json);
    out.close();
  }
}

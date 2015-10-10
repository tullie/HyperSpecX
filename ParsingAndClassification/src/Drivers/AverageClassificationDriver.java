package Drivers;

import MineralClassification.AverageSpectralClassifier;
import MineralClassification.MineralClassifierI;
import MineralClassification.MineralDataToJson;
import MineralParsing.MineralGridI;
import MineralParsing.Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AverageClassificationDriver {
  public static void main(String[] arg) throws FileNotFoundException {

    // Parse files.
    Parse parse = new Parse();
    MineralGridI unresolvedMineralGrid = parse.parseFilesToMineralData(1000, 124);

    // Classify
    MineralClassifierI classifier = new AverageSpectralClassifier();
    MineralGridI resolvedMineralGrid = classifier.predictMineralData(unresolvedMineralGrid);

    // Parse classified minerals to JSON.
    String json = MineralDataToJson.parseResolvedMineralData(resolvedMineralGrid);

    // Write JSON to file.
    PrintWriter out = new PrintWriter(new File("mineralData.json"));
    out.write(json);
    out.close();
    System.out.println("File created: mineralData.json");
  }
}


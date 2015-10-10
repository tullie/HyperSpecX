package MineralParsing;

import MineralClassification.MineralDataToJson;
import com.opencsv.CSVReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Parse {
  private CSVReader sclr;
  private String[] minerals;
  private String[] line;
  private BufferedImage img;

  public MineralGrid parseFilesToMineralData(int blockHeight, int centerWidth, int chunks) {
    loadImage();
    loadExcel();
    MineralGrid md = new MineralGrid(img.getWidth(), blockHeight);
    Map<String, Double> mineralMap = null;
    for (int k = 0; k < chunks; k++) {
      System.out.println("Writing chunk: "+(k+1));
      md.clear();
      for (int y = blockHeight*k; y < blockHeight*(k+1); y++) {
        //get next excel line
        if (y % centerWidth == 0) {
          mineralMap = readExcelLine();
        }

        for (int x = 0; x < img.getWidth(); x++) {
          Map<String, Double> tempMineralMap = null;
          //setmap if x is within centre block
          if (x >= (img.getWidth() / 2) - (centerWidth / 2)
                  && x <= (img.getWidth() / 2) + (centerWidth / 2)) {
            tempMineralMap = mineralMap;
          }
          //add new pixel data to the current grid
          Color mycolor = new Color(img.getRGB(x, y));
          md.addDatum(new MineralDatum(tempMineralMap,
                  mycolor.getRed(),
                  mycolor.getGreen(),
                  mycolor.getBlue()));
        }
      }
      //create a file of the current grid
      writeToJson(md,k+1);
    }
    return md;
  }

  private void writeToJson(MineralGridI md, int index){
    // Parse classified minerals to JSON.
    String json = MineralDataToJson.parseResolvedMineralData(md);

    // Write JSON to file.
    PrintWriter out = null;
    try {
      out = new PrintWriter(new File("resources/chunks/chunk_"+index+".json"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    out.write(json);
    out.close();
  }

  private Map<String, Double> readExcelLine() {
    try {
      line = sclr.readNext();
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
    Map<String, Double> pairs = new HashMap<>();
    for (int i = 229; i < minerals.length; i++) {
      try {
        if (!(line[i].equals("0") || line[i].equals("NULL"))) {
          pairs.put(minerals[i], Double.valueOf(line[i]));
        }
      } catch (Exception e) {
        pairs.put(minerals[i], 0.0);
      }
    }
    return pairs;
  }

  private void loadImage() {
    try {
      img = ImageIO.read(new File("resources/3240_core_image.bmp"));
    } catch (Exception e) {
      System.out.println("error: " + e);
    }
  }

  private void loadExcel() {
    try {
      String filename = "resources/3240_tsg_ds_sclrVS.csv";
      sclr = new CSVReader(new FileReader(filename));
      minerals = sclr.readNext();
      System.out.printf("\n");
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }
}

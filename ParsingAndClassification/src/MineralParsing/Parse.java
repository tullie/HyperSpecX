package MineralParsing;

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

  public MineralGrid parseFilesToMineralData(int blockHeight, int centerWidth) {
    loadImage();
    loadExcel();
    MineralGrid md = new MineralGrid(img.getWidth(), blockHeight);
    Map<String, Double> mineralMap = null;
    for (int y = 0; y < blockHeight; y++) {
      if (y % centerWidth == 0) {
        mineralMap = readExcelLine();
      }

      for (int x = 0; x < img.getWidth(); x++) {
        Map<String, Double> tempMineralMap = null;
        if (x >= (img.getWidth() / 2) - (centerWidth / 2)
            && x <= (img.getWidth() / 2) + (centerWidth / 2)) {
          tempMineralMap = mineralMap;
        }
        Color mycolor = new Color(img.getRGB(x, y));
        md.addDatum(new MineralDatum(tempMineralMap,
                                     mycolor.getRed(),
                                     mycolor.getGreen(),
                                     mycolor.getBlue()));
      }
    }
    return md;
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

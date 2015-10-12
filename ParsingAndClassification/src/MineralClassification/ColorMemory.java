package MineralClassification;

import MineralParsing.MineralDatum;
import MineralParsing.MineralDatumI;
import MineralParsing.MineralGrid;
import MineralParsing.MineralGridI;
import com.opencsv.CSVReader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by Konrad on 11/10/2015.
 */
public class ColorMemory {
    private static final double DIFF = 8.5;
    private List<List<MineralDatumI>> array;

    public ColorMemory(MineralGridI bla) {
        array = bla.getMineralRect();

        runRavi();
    }

    private void runRavi() {
        Map<Color, String>  mineralByColor = new HashMap<>();
        Map<String, Color> colorByMineral = new HashMap<>();

        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < 726; j++) {
                if( j >= 301 && j <= 425){
                    MineralDatumI minDat = array.get(i).get(j);
                    Color mycolor = new Color(roundUp(minDat.getRed()),roundUp(minDat.getGreen()), roundUp(minDat.getBlue()));
                    for (Map.Entry<String, Double> mineralPerc : minDat.getMinerals().entrySet()) {
                      if (!colorByMineral.containsKey(mineralPerc.getKey())) {
                            mineralByColor.put(mycolor, mineralPerc.getKey());
                            colorByMineral.put(mineralPerc.getKey(), mycolor);
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < 726; j++) {
                MineralDatumI minDat = array.get(i).get(j);
                Color mycolor = new Color(roundUp(minDat.getRed()), roundUp(minDat.getGreen()), roundUp(minDat.getBlue()));
                if (mineralByColor.containsKey(mycolor)) {
                    Map<String, Double> mineralsToSet = new HashMap<>();
                    String mineralName = mineralByColor.get(mycolor);
                    if (minDat.getMinerals() == null) {
                        mineralsToSet.put(mineralName, Math.random());
                    } else {
                        mineralsToSet = minDat.getMinerals();
                        mineralsToSet.put(mineralName, Math.random());
                    }
                    minDat.setMinerals(mineralsToSet);
                }
            }
        }
    }

    private void runTullie() {
        Map<Color, Map<String, Double>> mineralsByColor = new HashMap<>();

        //making csv
        String[] minerals = null;
        try {
            String filename = "resources/3240_tsg_ds_sclrVS.csv";
            CSVReader sclr = new CSVReader(new FileReader(filename));
            minerals = sclr.readNext();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        //creting mineral name list
        ArrayList<String> mineralNames = new ArrayList<>();
        for (int i = 229; i < minerals.length; i++) {
            mineralNames.add(minerals[i]);
        }

        //loop though small section
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < 726; j++) {
                if( j >= 301 && j <= 425){
                    MineralDatumI minDat = array.get(i).get(j);
                    Color mycolor = new Color(roundUp(minDat.getRed()),roundUp(minDat.getGreen()), roundUp(minDat.getBlue()));
                    if (!mineralsByColor.containsKey(mycolor)) {
                        mineralsByColor.put(mycolor, minDat.getMinerals());
                    }
                }
            }
        }

        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < 726; j++) {
                MineralDatumI minDat = array.get(i).get(j);
                Color mycolor = new Color(roundUp(minDat.getRed()), roundUp(minDat.getGreen()), roundUp(minDat.getBlue()));
                if (mineralsByColor.containsKey(mycolor)) {
                    Map<String, Double> mineralsToSet;
                    if (minDat.getMinerals() == null) {
                        mineralsToSet = mineralsByColor.get(mycolor);
                    } else {
                        mineralsToSet = minDat.getMinerals();
                    }
                    mineralsToSet = randomNoise(mineralsToSet);
                    minDat.setMinerals(mineralsToSet);
                }
            }
        }
    }

    Map<String, Double> randomNoise(Map<String, Double> map) {
        Map<String, Double> res = new HashMap<>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            res.put(entry.getKey(), Math.random());
        }

        return res;
    }

    int roundUp(int n) {
        int d  = (n + 59) / 60 * 60;
        return d > 255 ? 255 : d;
    }

    public MineralGridI getGrid(int height) {
        MineralGridI temp = new MineralGrid(726, height);
        temp.setMineralRect(array);
        return temp;
    }
}

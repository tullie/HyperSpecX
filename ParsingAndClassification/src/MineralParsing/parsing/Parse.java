package MineralParsing.parsing;

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
=======
import MineralParsing.interfaces.MineralDataI;
import MineralParsing.interfaces.ResolvedMineralDatumI;
>>>>>>> pushing
import com.opencsv.CSVReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
=======
import java.io.*;
import java.util.*;
>>>>>>> pushing

public class Parse {
    private CSVReader sclr;
    private String[] minerals;
    private String[] line;
    private

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
    public void run(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/3240_core_image.bmp"));
        } catch (IOException e) {
            System.out.println("error: "+e);
        }

        MineralData md = new MineralData();
        for (int y  = 0; y < 1000; y++) {
            for (int x = 0; x < 726; x++) {
                MineralDatum pixel = new MineralDatum();
                Rgb rgb = new Rgb();
                Map<String, Double> minerals = new Map<String, Double>();
            }
        }
//        try{
//            String filename = "res/3240_core_image.tif";
//            sclr = new CSVReader(new FileReader(filename));
//            minerals = sclr.readNext();
//            line = sclr.readNext();
//            int devision = 10;
//            for (int i = 0; i < devision; i++) {
//               parseBlock();
//            }
//        }catch (IOException e){
//            System.out.println("Error: " + e);
//        }
    }

    private void parseBlock() {
=======
    public MineralData run(){
        loadImage();
        loadExcel();
        MineralData md = new MineralData(img.getWidth());
        Map<String,Double> mineralMap = null;
        for (int y  = 0; y < 100; y++) {
            if( y % 124 == 0 ){
               mineralMap = readExcelLine();
            }
            if( y % 100 == 0 ) System.out.println(y);
            for (int x = 0; x < 726; x++) {
                Map<String,Double> tempmap = null;
                boolean set = false;
                if( x >= 301 && x <= 425 ){
                   tempmap = mineralMap;
                    set = true;
                }
                Color mycolor = new Color(img.getRGB(x, y));
                md.addDatum(new MineralDatum(tempmap,null,set, mycolor.getRed(),mycolor.getGreen(),mycolor.getBlue()));
            }
        }
        String[] shortminerals = new String[minerals.length-229];
        for (int i = 229; i < minerals.length; i++) {
          shortminerals[i-229] = minerals[i];
        }
        JSONObject obj = new JSONObject();
        JSONArray arr =  new JSONArray();

        Collections.addAll(arr, shortminerals);
        obj.put("minerals",arr);
        String mystring = obj.toString();
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File("minerals.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.write(mystring);
        out.close();
        return md;
    }

    private Map<String, Double> readExcelLine(){
        try{
            line = sclr.readNext();
        }catch (IOException e){
            System.out.println("Error: " + e);
        }
        Map<String, Double> pairs = new HashMap<>();
        for (int i = 229; i < minerals.length; i++) {
            try {
                if( !(line[i].equals("0") || line[i].equals("NULL")) ){
                    pairs.put(minerals[i], Double.valueOf(line[i]));
                }
            }catch(Exception e ){
                pairs.put(minerals[i],0.0);
            }
        }
        return pairs;
    }
>>>>>>> pushing


    }
}

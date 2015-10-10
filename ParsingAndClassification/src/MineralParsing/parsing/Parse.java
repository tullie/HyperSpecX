package MineralParsing.parsing;

import com.opencsv.CSVReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Parse {
    private CSVReader sclr;
    private String[] minerals;
    private String[] line;
    private

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


    }
}

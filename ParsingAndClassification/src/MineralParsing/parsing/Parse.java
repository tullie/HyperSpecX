package MineralParsing.parsing;

import com.opencsv.CSVReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Parse {
    private CSVReader sclr;
    private String[] minerals;
    private String[] line;
    public Parse(){

    }

    public void run(){
        try{
            String filename = "res/3240_core_image.tif";
            sclr = new CSVReader(new FileReader(filename));
            minerals = sclr.readNext();
            line = sclr.readNext();
            int devision = 10;
            for (int i = 0; i < devision; i++) {
               parseBlock();
            }
        }catch (IOException e){
            System.out.println("Error: " + e);
        }
    }

    private void parseBlock() {


    }
}

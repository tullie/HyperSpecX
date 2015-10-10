package MineralParsing.parsing;

import com.opencsv.CSVReader;

import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] arg) {

        System.out.println("Starting program");
        Parse parse = new Parse();
        parse.run();
        System.out.println("Finishing program");
    }
}

package MineralParsing.parsing;

import MineralParsing.interfaces.RgbI;

public class Rgb implements RgbI{

    private int red;
    private int green;
    private int blue;

    public Rgb(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public int getRed() {
        return 0;
    }

    @Override
    public int getGreen() {
        return 0;
    }

    @Override
    public int getBlue() {
        return 0;
    }
}

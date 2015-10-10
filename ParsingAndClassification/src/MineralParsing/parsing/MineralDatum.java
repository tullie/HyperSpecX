package MineralParsing.parsing;


import MineralParsing.interfaces.MineralDatumI;
import MineralParsing.interfaces.RgbI;

import java.util.Map;

/**
 * Created by ravi on 10/10/15.
 */
public class MineralDatum implements MineralDatumI {

    private Map<String, Integer> above;
    private Map<String, Integer> under;
    private RgbI color;
    private boolean reflectance;

    public MineralDatum(Map<String, Integer> above, Map<String, Integer> under, RgbI color, boolean reflectance) {
        this.above = above;
        this.under = under;
        this.color = color;
        this.reflectance = reflectance;
    }

    @Override
    public RgbI getColor() {
        return color;
    }

    @Override
    public Map<String, Integer> getUnderReflectanceByMineral() {
        return under;
    }

    @Override
    public Map<String, Integer> getAboveReflectanceByMineral() {
        return above;
    }

    @Override
    public boolean reflectanceSet() {
        return reflectance;
    }
}

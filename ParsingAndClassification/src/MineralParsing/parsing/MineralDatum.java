package MineralParsing.parsing;


<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
import MineralParsing.interfaces.MineralDatumI;
import MineralParsing.interfaces.RgbI;
=======
import MineralParsing.interfaces.ResolvedMineralDatumI;
import MineralParsing.interfaces.UnresolvedMineralDatumI;
>>>>>>> pushing

import java.util.Map;

/**
 * Created by ravi on 10/10/15.
 */
<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
public class MineralDatum implements MineralDatumI {
=======
public class MineralDatum implements ResolvedMineralDatumI {
>>>>>>> pushing

    private Map<String, Integer> above;
    private Map<String, Integer> under;
    private RgbI color;
    private boolean reflectance;

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
    public MineralDatum(Map<String, Integer> above, Map<String, Integer> under, RgbI color, boolean reflectance) {
        this.above = above;
        this.under = under;
        this.color = color;
        this.reflectance = reflectance;
=======
    public MineralDatum(Map<String, Double> above, Map<String, Double> under, boolean reflectance,int red, int green, int blue) {
            this.above = above;
            this.under = under;
            this.reflectance = reflectance;
            this.red = red;
            this.green = green;
        this.blue = blue;

    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
>>>>>>> pushing
    }

    @Override
    public RgbI getColor() {
        return color;
    }

    @Override
<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
    public Map<String, Integer> getUnderReflectanceByMineral() {
        return under;
    }

    @Override
    public Map<String, Integer> getAboveReflectanceByMineral() {
=======
    public Map<String, Double> getMinerals() {
>>>>>>> pushing
        return above;
    }

}

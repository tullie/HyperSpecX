package MineralParsing.parsing;

import MineralParsing.interfaces.MineralDataI;
import MineralParsing.interfaces.MineralDatumI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MineralData implements MineralDataI{
    private List<MineralDatum> rect;
    private int width;

    public MineralData(int width) {
        this.width = width;
        rect = new ArrayList<>();
    }

    @Override
    public List<List<MineralDatumI>> getMineralRect() {
        List<List<MineralDatumI>> list =  new ArrayList<>();
        for (int j = 0; j < list.size() / width; ++j) {
            List<MineralDatumI> row = new ArrayList<>();
            for (int i = 0; i < width; ++i) {
                row.add(rect.get((width * j) + i));
            }
            list.add(row);
        }
        return list;
    }

    public void addDatum(MineralDatum m) {
        rect.add(m);
    }
}

package MineralParsing.parsing;

import MineralParsing.interfaces.MineralDataI;
import MineralParsing.interfaces.MineralDatumI;

import java.util.List;

public class MineralData implements MineralDataI{

    private List<List<MineralDatumI>> rect;

    public MineralData(List<List<MineralDatumI>> rect) {
        this.rect = rect;
    }

    @Override
    public List<List<MineralDatumI>> getMineralRect() {
        return null;
    }
}

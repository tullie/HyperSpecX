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
<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
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
=======
    public List<List<UnresolvedMineralDatumI>> getUnresolvedMineralRect() {
//        List<List<UnresolvedMineralDatumI>> list =  new ArrayList<>();
//        for (int j = 0; j < rect.size() / width; ++j) {
//            List<UnresolvedMineralDatumI> row = new ArrayList<>();
//            for (int i = 0; i < width; ++i) {
//                row.add(rect.get((width * j) + i));
//            }
//            list.add(row);
//        }
//        return list;
        return null;
>>>>>>> pushing
    }

    public void addDatum(MineralDatum m) {
        rect.add(m);
    }
<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
=======


    @Override
    public List<List<ResolvedMineralDatumI>> getResolvedMineralRect() {
        List<List<ResolvedMineralDatumI>> list =  new ArrayList<>();
        for (int j = 0; j < 100; ++j) {
            List<ResolvedMineralDatumI> row = new ArrayList<>();
            for (int i = 0; i < width; ++i) {
                row.add(rect.get((width * j) + i));
            }
            list.add(row);
        }
        return list;
    }

    @Override
    public void setResolvedMineralRect(List<List<ResolvedMineralDatumI>> resolvedMineralRect) {

    }
>>>>>>> pushing
}

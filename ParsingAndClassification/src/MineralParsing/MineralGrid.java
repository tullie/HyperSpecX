package MineralParsing;

import java.util.ArrayList;
import java.util.List;

public class MineralGrid implements MineralGridI {
  private List<MineralDatumI> rect;
  private int width;
  private int height;

  public MineralGrid(int width, int height) {
    this.width = width;
    this.height = height;
    rect = new ArrayList<>(width * height);
  }

  public void addDatum(MineralDatum m) {
    rect.add(m);
  }

  public void clear(){
    rect.clear();
  }

  @Override public List<List<MineralDatumI>> getMineralRect() {
    List<List<MineralDatumI>> list = new ArrayList<>(height);
    for (int j = 0; j < height; ++j) {
      List<MineralDatumI> row = new ArrayList<>(width);
      for (int i = 0; i < width; ++i) {
        int index = (width * j) + i;
        row.add(rect.get(index));
      }
      list.add(row);
    }
    return list;
  }

  @Override public void setMineralRect(List<List<MineralDatumI>> mineralRect) {
    rect.clear();
    for (List<MineralDatumI> row : mineralRect) {
      for (MineralDatumI datum : row) {
        rect.add(datum);
      }
    }
  }
}

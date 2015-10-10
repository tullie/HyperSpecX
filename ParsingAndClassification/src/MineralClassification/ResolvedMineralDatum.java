package MineralClassification;

<<<<<<< c5f6f3b787aedfa94cc5af106aa9649f38b5b85f
=======
import MineralParsing.interfaces.ResolvedMineralDatumI;

>>>>>>> pushing
import java.util.HashMap;
import java.util.Map;

public class ResolvedMineralDatum implements ResolvedMineralDatumI {
  int red;
  int green;
  int blue;
  Map<String, Double> minerals = new HashMap<>();

  public ResolvedMineralDatum(int red, int green, int blue, Map<String, Double> minerals) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.minerals = minerals;
  }

  @Override public int getRed() {
    return red;
  }

  @Override public int getGreen() {
    return green;
  }

  @Override public int getBlue() {
    return blue;
  }

  @Override public Map<String, Double> getMinerals() {
    return new HashMap<>();
  }
}

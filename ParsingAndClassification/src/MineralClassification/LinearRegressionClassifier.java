package MineralClassification;

import MineralParsing.MineralDatumI;
import MineralParsing.MineralGridI;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import scala.Tuple2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinearRegressionClassifier implements MineralClassifierI, Serializable {
  static final double PREDICTION_THRESHOLD = 0.1;

  @Override public MineralGridI predictMineralData(MineralGridI rawMineralData) {
    LinearRegressionModel model = learnFromMineralData(rawMineralData);

    List<List<MineralDatumI>> mineralRect = rawMineralData.getMineralRect();
    for (int i = 0; i < mineralRect.size(); ++i) {
      Map<String, Double> mineralsForDatum = new HashMap<>();
      for (int j = 0; j < mineralRect.get(i).size(); ++j) {
        for (int k = 0; k < MineralUtil.mineralCount(); ++k) {
          MineralDatumI datum = mineralRect.get(i).get(j);
          double[] features = {(double) datum.getRed(),
                               (double) datum.getGreen(),
                               (double) datum.getBlue(), k};
          double predictionPercent = model.predict(new DenseVector(features));
          System.out.println("Prediction: " + predictionPercent);
          if (predictionPercent > PREDICTION_THRESHOLD) {
            mineralsForDatum.put(MineralUtil.mineralFromId(k), predictionPercent);
          }
        }
        rawMineralData.setMineralsForDatum(i, j, mineralsForDatum);
      }
    }

    return rawMineralData;
  }

  LinearRegressionModel learnFromMineralData(MineralGridI mineralData) {
    SparkConf conf = new SparkConf();
    conf.setAppName("Core Mineral Classifier");
    conf.setMaster("local");
    JavaSparkContext sc = new JavaSparkContext(conf);

    JavaRDD<LabeledPoint> data = getLabelPointsFromMineralData(sc, mineralData);
    data.cache();

    // Building the model
    int numIterations = 100;
    final LinearRegressionModel model = LinearRegressionWithSGD
        .train(JavaRDD.toRDD(data), numIterations);

    // Evaluate model on training examples and compute training error
    JavaRDD<Tuple2<Double, Double>> valuesAndPreds =
        data.map(point -> {
          double prediction = model.predict(point.features());
          return new Tuple2<>(prediction, point.label());
        });

    double MSE = new JavaDoubleRDD(valuesAndPreds.map(
        new Function<Tuple2<Double, Double>, Object>() {
          public Object call(Tuple2<Double, Double> pair) {
            return Math.pow(pair._1() - pair._2(), 2.0);
          }
        }
    ).rdd()).mean();

    System.out.println("Training Mean Squared Error = " + MSE);

    return model;
  }

  JavaRDD<LabeledPoint> getLabelPointsFromMineralData(JavaSparkContext sc,
      MineralGridI mineralGrid) {
    List<LabeledPoint> labeledInputs = new ArrayList<>();
    for (List<MineralDatumI> row : mineralGrid.getMineralRect()) {
      for (MineralDatumI datum : row) {
        if (datum.getMinerals() == null) {
          continue;
        }
        for (Map.Entry<String, Double> entry : datum.getMinerals().entrySet()) {
          double[] features = {(double) datum.getRed(),
              (double) datum.getBlue(),
              (double) datum.getGreen(),
              MineralUtil.mineralToId(entry.getKey())};
          LabeledPoint newPoint = new LabeledPoint(entry.getValue(), new DenseVector(features));
          labeledInputs.add(newPoint);
        }
      }
    }

    return sc.parallelize(labeledInputs);
  }
}


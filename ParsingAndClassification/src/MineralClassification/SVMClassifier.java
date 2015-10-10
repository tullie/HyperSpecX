package MineralClassification;

import MineralParsing.MineralDatumI;
import MineralParsing.MineralGrid;
import MineralParsing.MineralGridI;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.regression.LabeledPoint;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SVMClassifier implements MineralClassifierI {
  @Override public MineralGridI predictMineralData(MineralGridI rawMineralData) {
    List<List<MineralDatumI>> resolvedMineralData = new ArrayList<>();

    SparkConf conf = new SparkConf();
    conf.setAppName("Core Mineral Classifier");
    conf.setMaster("local");
    JavaSparkContext sc = new JavaSparkContext(conf);

    JavaRDD<LabeledPoint> data = getLabelPointsFromMineralData(sc, rawMineralData);

    // Split initial RDD into two... [60% training data, 40% testing data].
    JavaRDD<LabeledPoint> training = data.sample(false, 0.6, 11L);
    training.cache();
    JavaRDD<LabeledPoint> test = data.subtract(training);

    // Run training algorithm to build the model.
    int numIterations = 100;
    final SVMModel model = SVMWithSGD.train(training.rdd(), numIterations);
    model.clearThreshold();

    // Compute raw scores on the test set.
    JavaRDD<Tuple2<Object, Object>> scoreAndLabels = test.map(p -> {
      Double score = model.predict(p.features());
      return new Tuple2<>(score, p.label());
    });

    // Get evaluation metrics.
    BinaryClassificationMetrics metrics =
        new BinaryClassificationMetrics(JavaRDD.toRDD(scoreAndLabels));
    double auROC = metrics.areaUnderROC();

    System.out.println("Area under ROC = " + auROC);

    rawMineralData.setMineralRect(resolvedMineralData);
    return rawMineralData;
  }

  JavaRDD<LabeledPoint> getLabelPointsFromMineralData(JavaSparkContext sc,
                                                      MineralGridI mineralGrid) {
    List<LabeledPoint> labeledInputs = new ArrayList<>();
    for (List<MineralDatumI> row : mineralGrid.getMineralRect()) {
      for (MineralDatumI datum : row) {

        // TODO: Output values must be 0 or 1.
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

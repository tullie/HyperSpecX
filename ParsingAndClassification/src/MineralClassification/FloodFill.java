package MineralClassification;

import MineralParsing.MineralDatum;
import MineralParsing.MineralDatumI;
import MineralParsing.MineralGrid;
import MineralParsing.MineralGridI;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Konrad on 11/10/2015.
 */
public class FloodFill {
    private static final double DIFF = 14.5;
    private List<List<MineralDatumI>> array;

    public FloodFill(MineralGridI bla) {
        array = bla.getMineralRect();

        run();
    }

    private void run() {
        int x = 310;
        int y = 0;
        while (array.get(y).get(x).getMinerals().isEmpty()) {
           ++y;
        }
        System.out.println("x = " + x + " y = " + y);
        while (array.get(y).get(x).getMinerals() != null) {
            --x;
        }
        ++x;
        System.out.println("x = " + x + " y = " + y);

        int lefta = x;
        int topa = y;

        int bota = y;

        while (array.get(y).get(x).getMinerals() != null) {
            ++x;
        }
        --x;
        int righta = x;

        Deque<Point> bfs = new LinkedList<>();
        bfs.push(new Point(lefta, topa));
        bfs.push(new Point(lefta, bota));
        bfs.push(new Point(righta, topa));
        bfs.push(new Point(righta, bota));


        while(!bfs.isEmpty()) {
            Point p = bfs.pop();

            MineralDatumI minDa = array.get(p.y).get(p.x);
            Map<String, Double> minerals = minDa.getMinerals();

            try {
                MineralDatum left = (MineralDatum) array.get(p.y).get(p.x - 1);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x - 1, p.y));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            } catch (Exception e) {
            }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y - 1).get(p.x);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x, p.y - 1));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
                }catch(Exception e){
                }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y).get(p.x+1);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x+1, p.y));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            }catch(Exception e){
            }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y-1).get(p.x);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x, p.y-1));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            }catch(Exception e){
            }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y-1).get(p.x-1);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x-1, p.y-1));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            }catch(Exception e){
            }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y+1).get(p.x+1);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x+1, p.y+1));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            }catch(Exception e){
            }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y-1).get(p.x+1);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x+1, p.y-1));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            }catch(Exception e){
            }
            try {
                MineralDatum left = (MineralDatum) array.get(p.y+1).get(p.x-1);
                double orig = (minDa.getBlue() + minDa.getGreen() + minDa.getRed()) / 3.0;
                double right = (left.getBlue() + left.getGreen() + left.getRed()) / 3.0;
                if (Math.abs(orig - right) < DIFF) {

                    if (left.getMinerals() == null) {
                        bfs.push(new Point(p.x-1, p.y+1));
                        Map<String, Double> temp = new HashMap<>();
                        for (Map.Entry<String, Double> entry : minerals.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Map.Entry<String, Double> entry : temp.entrySet()) {
                            double val = entry.getValue() - 0.001 - Math.random() * 0.005;
                            if (val > 0)
                            entry.setValue(val);
                            // TODO random decrease
                        }
                        left.setMinerals(temp);
                    }
                }
            }catch(Exception e){
            }
        }
    }

    public MineralGridI getGrid() {
        MineralGridI temp = new MineralGrid(726, 1000);
        temp.setMineralRect(array);
        return temp;
    }
}

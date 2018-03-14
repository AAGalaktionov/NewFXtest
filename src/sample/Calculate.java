package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Calculate {
    double x0 = 0.0;
    double y0 = 2.0;
/*    double tmpV0 = 10;
    double tmpAngle = 45;*/
    double h = 0.05;
    private InputText inputText;
    private ArrayList<Double> tmpVx = new ArrayList<>();
    private ArrayList<Double> tmpVy = new ArrayList<>();
    private ArrayList<Double> tmpX = new ArrayList<>();
    private ArrayList<Double> tmpY = new ArrayList<>();


    TreeMap<Double, Double> calculateTraectory(TreeMap<Double, Double> list, Double tmpV0, Double tmpAngle) {
        list.clear();
        tmpVx.clear();
        tmpVy.clear();
        tmpY.clear();
        tmpX.clear();
        tmpVx.add( tmpV0 * Math.cos(tmpAngle*Math.PI/180));

        tmpVy.add(tmpV0 * Math.sin(tmpAngle*Math.PI/180));
        tmpX.add(x0);
        tmpY.add(y0);
        list.put(tmpX.get(0), tmpY.get(0));
        for (int i = 1; i < 100; i++) {
            tmpVx.add(tmpVx.get(i-1));
            tmpVy.add(tmpVy.get(i-1) - h * 9.8);
            tmpX.add(tmpX.get(i-1) + h * tmpVx.get(i));
            tmpY.add(tmpY.get(i-1) + h * tmpVy.get(i));
            if(tmpY.get(i) > 0)
            list.put((double) Math.round(tmpX.get(i)*1000)/1000, (double) Math.round(tmpY.get(i)*1000)/1000);
        }

        return list;
    }

    public Calculate(InputText inputText) {
        this.inputText = inputText;
    }
    public Calculate() {

    }

}

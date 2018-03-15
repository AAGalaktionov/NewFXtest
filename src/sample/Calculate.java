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


    private ArrayList<Double> antmpX = new ArrayList<>();
    private ArrayList<Double> antmpY = new ArrayList<>();

    private ArrayList<Double> airtmpVx = new ArrayList<>();
    private ArrayList<Double> airtmpVy = new ArrayList<>();
    private ArrayList<Double> airtmpX = new ArrayList<>();
    private ArrayList<Double> airtmpY = new ArrayList<>();

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


    TreeMap<Double, Double> calculateAnaliticalTraectory(TreeMap<Double, Double> list, Double tmpV0, Double tmpAngle) {
        list.clear();

        antmpY.clear();
        antmpX.clear();
        antmpX.add(x0);
        antmpY.add(y0);
        list.put(antmpX.get(0), antmpY.get(0));
        double time = 0.0;
        for (int i = 1; i < 100; i++) {
            time+=0.05;
            antmpX.add(antmpX.get(0) + tmpV0*Math.cos(tmpAngle*Math.PI/180)*time);
            antmpY.add(antmpY.get(0) + tmpV0*Math.sin(tmpAngle*Math.PI/180)*time - 9.8*time*time/2);
            if(antmpY.get(i) > 0)
                /*list.put((double) Math.round(antmpX.get(i)*1000)/1000, (double) Math.round(antmpY.get(i)*1000)/1000);*/
            list.put( antmpX.get(i), antmpY.get(i));
        }

        return list;
    }


    TreeMap<Double, Double> calculateAirResTraectory(TreeMap<Double, Double> list, Double tmpV0, Double tmpAngle) {
        list.clear();
        airtmpVx.clear();
        airtmpVy.clear();
        airtmpY.clear();
        airtmpX.clear();
        airtmpVx.add( tmpV0 * Math.cos(tmpAngle*Math.PI/180));

        airtmpVy.add(tmpV0 * Math.sin(tmpAngle*Math.PI/180));
        airtmpX.add(x0);
        airtmpY.add(y0);
        list.put(airtmpX.get(0), airtmpY.get(0));
        for (int i = 1; i < 100; i++) {
            airtmpVx.add(airtmpVx.get(i-1) - 6*Math.PI*0.0182*0.122*airtmpVx.get(i-1));
            airtmpVy.add(airtmpVy.get(i-1) - h * 9.8 - 6*Math.PI*0.0182*0.122*airtmpVy.get(i-1));
            airtmpX.add(airtmpX.get(i-1) + h * airtmpVx.get(i));
            airtmpY.add(airtmpY.get(i-1) + h * airtmpVy.get(i));
            if(airtmpY.get(i) > 0)
                list.put((double) Math.round(airtmpX.get(i)*1000)/1000, (double) Math.round(airtmpY.get(i)*1000)/1000);
        }

        return list;
    }

    public Calculate(InputText inputText) {
        this.inputText = inputText;
    }
    public Calculate() {

    }

}

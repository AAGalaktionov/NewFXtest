package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jdk.nashorn.api.tree.Tree;

import java.util.HashMap;
import java.util.TreeMap;

public class Controller {


    @FXML
    private Button button;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private LineChart lineChart;
    @FXML
    private ToggleButton toggleAnalytically;
    @FXML
    private ToggleButton toggleNumerical;
    @FXML
    private ToggleButton toggleAirResistance;

    @FXML
    public void initialize() {
        TreeMap<Double, Double> line1 = new TreeMap<>();
        TreeMap<Double, Double> line2 = new TreeMap<>();
        TreeMap<Double, Double> line3 = new TreeMap<>();
        TreeMap<Double, Double> line4 = new TreeMap<>();





        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                InputText inputText = new InputText(Double.valueOf(textField2.getText()), Double.valueOf(textField1.getText()));
                System.out.println(inputText.getAngle() + " " + inputText.getSpeed());
                Calculate calculate = new Calculate();
                Calculate calculate2 = new Calculate();

                if(toggleNumerical.isSelected())
                calculate.calculateTraectory(line1, inputText.getSpeed(), inputText.getAngle());

                if(toggleAnalytically.isSelected())
                    calculate2.calculateAnaliticalTraectory(line3, inputText.getSpeed(), inputText.getAngle());

                if(toggleAirResistance.isSelected())
                    calculate2.calculateAirResTraectory(line4, inputText.getSpeed(), inputText.getAngle());


/*********************************************************************************************************/
                /**TOGGLE**/
/*********************************************************************************************************/
                System.out.println("NUMERICAL!!!" +toggleNumerical.isSelected());
                System.out.println("Analytically!!!" +toggleAnalytically.isSelected());
                System.out.println("AirResistance!!!" +toggleAirResistance.isSelected());
/*********************************************************************************************************/


            } catch (NumberFormatException e) {
                System.out.println("Некоректные входные данные!!!");
            }
            //Очистка нужна, если нажмут на кнопку несколько раз
            lineChart.getData().clear();
          //  lineChart.setHorizontalGridLinesVisible(true);


            //По нажатию рисуем линии, тестовый убогий код писать подругому, это для понимания



            line2.put(4.0, 3.05);
            line2.put(4.45, 3.05);
            line2.put(4.60, 3.05);
            line2.put(4.601, 3.95);
            line2.put(4.602, 2.75);

            XYChart.Series seriesForLine1 = new XYChart.Series();
            seriesForLine1.setName("Numerical");
            for (HashMap.Entry<Double, Double> entry : line1.entrySet()) {
                seriesForLine1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }

            XYChart.Series seriesForLine3 = new XYChart.Series();
            seriesForLine3.setName("Analitically");
            for (HashMap.Entry<Double, Double> entry : line3.entrySet()) {
                seriesForLine3.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }

            XYChart.Series seriesForLine4 = new XYChart.Series();
            seriesForLine4.setName("Air Resistance");
            for (HashMap.Entry<Double, Double> entry : line4.entrySet()) {
                seriesForLine4.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }


            XYChart.Series seriesForLine2 = new XYChart.Series();
            // seriesForLine2.nodeProperty();
            seriesForLine2.setName("Basket");
            for (HashMap.Entry<Double, Double> entry : line2.entrySet()) {
                seriesForLine2.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }

            lineChart.getData().addAll(seriesForLine1, seriesForLine3, seriesForLine4, seriesForLine2);


        });
        textField1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            textField1.setText("");
        });
        textField2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            textField2.setText("");
        });


    }
}

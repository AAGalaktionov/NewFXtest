package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;
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
        Map<Double, Double> line2 = new TreeMap<>();
        lineChart.getData().clear();
        lineChart.setAnimated(false);

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                InputText inputText = new InputText(Double.valueOf(textField2.getText()), Double.valueOf(textField1.getText()));
                System.out.println(inputText.getAngle() + " " + inputText.getSpeed());
                XYChart.Series seriesForLine1 = new XYChart.Series();
                seriesForLine1.setName("Numerical");


                XYChart.Series seriesForLine3 = new XYChart.Series();
                seriesForLine3.setName("Analitically");

                XYChart.Series seriesForLine4 = new XYChart.Series();
                seriesForLine4.setName("Air Resistance");

                if (toggleNumerical.isSelected()) {
                    for (Map.Entry<Double, Double> entry : Calculate.calculateTraectory(inputText.getSpeed(), inputText.getAngle()).entrySet()) {
                        seriesForLine1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                    }
                }
                if (toggleAnalytically.isSelected()) {
                    for (Map.Entry<Double, Double> entry : Calculate.calculateAnaliticalTraectory(inputText.getSpeed(), inputText.getAngle()).entrySet()) {
                        seriesForLine3.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                    }
                }
                if (toggleAirResistance.isSelected()) {
                    for (Map.Entry<Double, Double> entry : Calculate.calculateAirResTraectory(inputText.getSpeed(), inputText.getAngle()).entrySet()) {
                        seriesForLine4.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                    }
                }


/*********************************************************************************************************/
                /**TOGGLE**/
/*********************************************************************************************************/
                System.out.println("NUMERICAL!!!" + toggleNumerical.isSelected());
                System.out.println("Analytically!!!" + toggleAnalytically.isSelected());
                System.out.println("AirResistance!!!" + toggleAirResistance.isSelected());
/*********************************************************************************************************/


                //Очистка нужна, если нажмут на кнопку несколько раз
                lineChart.getData().clear();
                //  lineChart.setHorizontalGridLinesVisible(true);


                //По нажатию рисуем линии, тестовый убогий код писать подругому, это для понимания


                line2.put(4.0, 3.05);
                line2.put(4.45, 3.05);
                line2.put(4.60, 3.05);
                line2.put(4.601, 3.95);
                line2.put(4.602, 2.75);


                XYChart.Series seriesForLine2 = new XYChart.Series();
                // seriesForLine2.nodeProperty();
                seriesForLine2.setName("Basket");
                for (HashMap.Entry<Double, Double> entry : line2.entrySet()) {
                    seriesForLine2.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }

                lineChart.getData().addAll(seriesForLine1, seriesForLine3, seriesForLine4, seriesForLine2);

            } catch (NumberFormatException e) {
                System.out.println("Некоректные входные данные!!!");
            }


        });
        textField1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            textField1.setText("");
        });
        textField2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            textField2.setText("");
        });


    }
}

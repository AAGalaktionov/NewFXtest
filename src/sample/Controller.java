package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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
    public void initialize() {
        TreeMap<Double, Double> line1 = new TreeMap<>();
        TreeMap<Double, Double> line2 = new TreeMap<>();





        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                InputText inputText = new InputText(Double.valueOf(textField2.getText()), Double.valueOf(textField1.getText()));
                System.out.println(inputText.getAngle() + " " + inputText.getSpeed());
                Calculate calculate = new Calculate();
                calculate.calculateTraectory(line1, inputText.getSpeed(), inputText.getAngle());
            } catch (NumberFormatException e) {
                System.out.println("Некоректные входные данные!!!");
            }
            //Очистка нужна, если нажмут на кнопку несколько раз
            lineChart.getData().clear();
            lineChart.setHorizontalGridLinesVisible(true);


            //По нажатию рисуем линии, тестовый убогий код писать подругому, это для понимания


            double tmp = 4.00;
            for (int i = 0; i < 5; i++) {
                line2.put(tmp, 3.05);
                tmp += 0.1;
            }
            line2.put(tmp-0.09, 4.05);

            XYChart.Series seriesForLine1 = new XYChart.Series();
            seriesForLine1.setName("Line one");
            for (HashMap.Entry<Double, Double> entry : line1.entrySet()) {
                seriesForLine1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }
            XYChart.Series seriesForLine2 = new XYChart.Series();
            // seriesForLine2.nodeProperty();
            seriesForLine2.setName("Line two");
            for (HashMap.Entry<Double, Double> entry : line2.entrySet()) {
                seriesForLine2.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }

            lineChart.getData().addAll(seriesForLine1, seriesForLine2);


        });
        textField1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            textField1.setText("");
        });
        textField2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            textField2.setText("");
        });


    }
}

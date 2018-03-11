package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;

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
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            InputText inputText = new InputText(Double.valueOf(textField2.getText()), Double.valueOf(textField1.getText()));
            System.out.println(inputText.getAngle() + " " + inputText.getSpeed());

            //По нажатию рисуем линии тестовый убогий код
            HashMap<Double, Double> line1 = new HashMap<>();
            HashMap<Double, Double> line2 = new HashMap<>();
            for (int i = 0; i < 11; i++) {
                line1.put(1.0 * i, 1.0 * i + i + 1);
                line2.put(1.0 * i, 2.0 * i + i + 2);
            }
            XYChart.Series seriesForLine1 = new XYChart.Series();
            seriesForLine1.setName("Line one");
            for (HashMap.Entry<Double, Double> entry : line1.entrySet()) {
                seriesForLine1.getData().add(new XYChart.Data(entry.getKey().toString(), entry.getValue()));
            }
            XYChart.Series seriesForLine2 = new XYChart.Series();
            seriesForLine2.setName("Line two");
            for (HashMap.Entry<Double, Double> entry : line2.entrySet()) {
                seriesForLine2.getData().add(new XYChart.Data(entry.getKey().toString(), entry.getValue()));
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

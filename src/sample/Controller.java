package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Random;

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
            try {
                InputText inputText = new InputText(Double.valueOf(textField2.getText()), Double.valueOf(textField1.getText()));
                System.out.println(inputText.getAngle() + " " + inputText.getSpeed());
            } catch (NumberFormatException e) {
                System.out.println("Некоректные входные данные!!!");
            }
            //Очистка нужна, если нажмут на кнопку несколько раз, попробуй)))
            lineChart.getData().clear();

            //По нажатию рисуем линии, тестовый убогий код писать подругому, это для понимания
            HashMap<Double, Double> line1 = new HashMap<>();
            HashMap<Double, Double> line2 = new HashMap<>();
            for (int i = 0; i < 11; i++) {
                Random random = new Random();
                double rand = random.nextDouble();
                line1.put(i * 1.0, rand * i + i + 1);
                line2.put(i * 1.0, rand * rand * i + 3);
            }


            XYChart.Series seriesForLine1 = new XYChart.Series();
            seriesForLine1.setName("Line one");
            line1.entrySet().forEach(p -> seriesForLine1.getData().add(new XYChart.Data(p.getKey().toString(), p.getValue())));
            XYChart.Series seriesForLine2 = new XYChart.Series();
            seriesForLine2.setName("Line two");
            line2.entrySet().forEach(p -> seriesForLine2.getData().add(new XYChart.Data(p.getKey().toString(), p.getValue())));

            lineChart.getData().addAll(seriesForLine1, seriesForLine2);


        });
        textField1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> textField1.setText(""));
        textField2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> textField2.setText(""));


    }
}

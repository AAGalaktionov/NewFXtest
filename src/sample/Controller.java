package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {


    @FXML
    private Button button;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;




    @FXML
    public void initialize() {




        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                InputText inputText = new InputText(Double.valueOf(textField2.getText()), Double.valueOf(textField1.getText()));
                textField2.setText("initial speed");
                textField1.setText("angle");
                System.out.println(inputText.getAngle() + " " + inputText.getSpeed());




            }
        });
    }
}

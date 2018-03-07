package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {


    @FXML
    private LineChart lineChart;


    XYChart.Series<Double, Double> series = new XYChart.Series();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Basket model");

        //Defining X axis
        NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
        xAxis.setLabel("Years");

        //Defining y axis
        NumberAxis yAxis = new NumberAxis(0, 350, 50);
        yAxis.setLabel("No.of schools");


        lineChart = new LineChart(xAxis, yAxis);
        series.getData().add(new XYChart.Data<>(0.0, 0.0));
        series.getData().add(new XYChart.Data<>(10.0, 10.0));
        series.getData().add(new XYChart.Data<>(300.0, 300.0));
        lineChart.getData().add(series);
        lineChart.setMaxWidth(1000);
        lineChart.setMaxHeight(1000);

        primaryStage.setScene(new Scene(root, 900, 600));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

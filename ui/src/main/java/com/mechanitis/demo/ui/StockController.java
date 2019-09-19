package com.mechanitis.demo.ui;

import com.mechanitis.demo.client.StubStockClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class StockController {

    @FXML
    private LineChart<String, String> chart;

    //this is the controller, and what it does is wires stuff together
    @FXML
    public void initialize() {
        //series is the UI (View) element
        final XYChart.Series<String,String> series = new XYChart.Series<>();
        //needs to be wired in to the data (Model)
        StockPriceWatcher stockPriceWatcher = new StockPriceWatcher();
        series.setData(stockPriceWatcher.getData());

        //a chart supports more than one series of data, so the data for the chart is a list of series
        //but we only have one element in this list, our single series
        ObservableList<XYChart.Series<String,String>> allSeriesForChart = observableArrayList(series);
        chart.setData(allSeriesForChart);

        StubStockClient stubStockClient = new StubStockClient();
        stubStockClient.pricesFor("FAKE").subscribe(stockPriceWatcher);
    }

    private class StockPriceWatcher implements Consumer<Double> {

        private ObservableList<XYChart.Data<String, String>> prices = FXCollections.observableArrayList();

        public ObservableList<XYChart.Data<String, String>> getData() {
            return prices;
        }

        @Override
        public void accept(Double aDouble) {
            System.out.println("aDouble = " + aDouble);
            Platform.runLater(() -> prices.add(new XYChart.Data<String, String>(String.valueOf(LocalDateTime.now().getSecond()),
                                                                                String.valueOf(aDouble))));
//            Platform.runLater(() -> prices.add(new XYChart.Data<String, String>("0", "1")));
        }
    }
}

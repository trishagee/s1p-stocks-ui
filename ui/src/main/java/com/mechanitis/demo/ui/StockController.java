package com.mechanitis.demo.ui;

import com.mechanitis.demo.client.StubStockClient;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static javafx.collections.FXCollections.observableArrayList;

@Component
public class StockController {

    @FXML
    private LineChart<String, Double> chart;

    //this is the controller, and what it does is wires stuff together
    @FXML
    public void initialize() {
        final String symbol = "FAKE";

        final StockPriceWatcher stockPriceWatcher = new StockPriceWatcher();
        new StubStockClient().pricesFor(symbol).subscribe(stockPriceWatcher);

        //series is the UI (View) element
        final Series<String, Double> series = new Series<>(symbol, stockPriceWatcher.getData());

        //a chart supports more than one series of data, so the data for the chart is a list of series
        //but we only have one element in this list, our single series
        chart.setData(observableArrayList(series));


        //A chart has multiple series
        //A series is a list of Data points
        //Each Data is a pair of String and Double
    }

    // The watcher is subscribed to a data source and updates the list that is displayed as a series
    private static class StockPriceWatcher implements Consumer<Double> {

        private ObservableList<Data<String, Double>> prices = observableArrayList();

        ObservableList<Data<String, Double>> getData() {
            return prices;
        }

        @Override
        public void accept(Double price) {
            Platform.runLater(() -> prices.add(new Data<>(valueOf(now().getSecond()), price)));
        }
    }
}

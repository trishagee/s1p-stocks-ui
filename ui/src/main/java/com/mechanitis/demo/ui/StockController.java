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
        final String symbol1 = "FAKE";
        final String symbol2 = "DUMMY";

        final StockPriceWatcher stockPriceWatcher1 = new StockPriceWatcher(symbol1);
        final StockPriceWatcher stockPriceWatcher2 = new StockPriceWatcher(symbol2);

        //series is the UI (View) element
        final Series<String, Double> series1 = new Series<>(symbol1, stockPriceWatcher1.getData());
        final Series<String, Double> series2 = new Series<>(symbol2, stockPriceWatcher2.getData());

        //a chart supports more than one series of data, so the data for the chart is a list of series
        //but we only have one element in this list, our single series
        chart.setData(observableArrayList(series1, series2));


        //A chart has multiple series
        //A series is a list of Data points
        //Each Data is a pair of String and Double
    }

    // The watcher subscribes to a data source and updates the list that is displayed as a series
    private static class StockPriceWatcher implements Consumer<Double> {

        private ObservableList<Data<String, Double>> prices = observableArrayList();

        StockPriceWatcher(String symbol) {
            //this can't stay like this, because we want to be able to switch out our clients easily
            StubStockClient.pricesFor(symbol).subscribe(this);
        }

        ObservableList<Data<String, Double>> getData() {
            return prices;
        }

        @Override
        public void accept(Double price) {
            Platform.runLater(() -> prices.add(new Data<>(valueOf(now().getSecond()), price)));
        }
    }
}

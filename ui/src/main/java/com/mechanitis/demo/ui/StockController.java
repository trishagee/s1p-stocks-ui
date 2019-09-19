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
        final StockPrices stockPrices1 = new StockPrices("FAKE");
        final StockPrices stockPrices2 = new StockPrices("DUMMY");

        //a chart supports more than one series of data, so the data for the chart is a list of series
        //but we only have one element in this list, our single series
        chart.setData(observableArrayList(stockPrices1.getSeries(), stockPrices2.getSeries()));

        //A chart has multiple series
        //A series is a list of Data points
        //Each Data is a pair of String and Double
    }

    // Subscribes to a data source and updates data to be displayed as a series
    // Model
    private static class StockPrices implements Consumer<Double> {

        private final ObservableList<Data<String, Double>> prices = observableArrayList();
        private final Series<String, Double> series;

        StockPrices(String symbol) {
            //this can't stay like this, because we want to be able to switch out our clients easily
            StubStockClient.pricesFor(symbol).subscribe(this);
            series = new Series<>(symbol, prices);
        }

        Series<String, Double> getSeries() {
            return series;
        }

        @Override
        public void accept(Double price) {
            Platform.runLater(() -> prices.add(new Data<>(valueOf(now().getSecond()), price)));
        }
    }
}

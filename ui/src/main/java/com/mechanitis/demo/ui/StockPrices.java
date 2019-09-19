package com.mechanitis.demo.ui;

import com.mechanitis.demo.client.StockClient;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import java.util.function.Consumer;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static javafx.collections.FXCollections.observableArrayList;

// Subscribes to a data source and updates data to be displayed as a series
// Model
class StockPrices implements Consumer<Double> {
    // this is the data structure that JavaFX will watch to see what to draw
    private final ObservableList<Data<String, Double>> prices = observableArrayList();
    // this is the UI element for the data, the Series
    private final Series<String, Double> series;

    StockPrices(String symbol, StockClient stockClient) {
        series = new Series<>(symbol, prices);
        stockClient.pricesFor(symbol).subscribe(this);
    }

    Series<String, Double> getSeries() {
        return series;
    }

    @Override
    public void accept(Double price) {
        Platform.runLater(() -> prices.add(new Data<>(valueOf(now().getSecond()), price)));
    }
}

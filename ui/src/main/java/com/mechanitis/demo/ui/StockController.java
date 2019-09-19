package com.mechanitis.demo.ui;

import com.mechanitis.demo.client.StockClient;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class StockController {

    @FXML
    private LineChart<String, Double> chart;
    private final StockClient stockClient;

    StockController(final StockClient stockClient) {
        this.stockClient = stockClient;
    }

    //this is the controller, and what it does is wires stuff together
    @FXML
    public void initialize() {
        final StockPrices stockPrices1 = new StockPrices("FAKE", stockClient);
        final StockPrices stockPrices2 = new StockPrices("DUMMY", stockClient);

        //a chart supports more than one series of data, so the data for the chart is a list of series
        chart.setData(observableArrayList(stockPrices1.getSeries(),
                                          stockPrices2.getSeries()));
    }

}

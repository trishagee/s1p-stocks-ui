package com.mechanitis.demo.ui;


import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UiApplication {

    public static void main(String[] args) {
        Application.launch(StockChartApplication.class, args);
    }

}

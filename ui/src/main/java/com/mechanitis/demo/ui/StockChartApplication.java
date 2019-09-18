package com.mechanitis.demo.ui;


import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class StockChartApplication extends Application {


    private ApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() throws Exception {
        this.context = new SpringApplicationBuilder()
                .sources(StockChartApplication.class)
                .run();
    }

    //TODO: stop

    private class StageReadyEvent {
        private Stage stage;

        public StageReadyEvent(Stage stage) {
            this.stage = stage;
        }
    }
}

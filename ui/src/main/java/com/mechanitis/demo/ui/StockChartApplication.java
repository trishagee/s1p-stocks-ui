package com.mechanitis.demo.ui;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

@SpringBootApplication
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

    //do we need this? I did run out of memory earlier....
    @Override
    public void stop() throws Exception {
//        this.context.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {

        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return (Stage)this.getSource();
        }
    }
}

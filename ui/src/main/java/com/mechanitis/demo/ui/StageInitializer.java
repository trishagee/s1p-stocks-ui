package com.mechanitis.demo.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StockChartApplication.StageReadyEvent> {

    private ApplicationContext applicationContext;

    StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StockChartApplication.StageReadyEvent stageReadyEvent) {

        try {
            Stage stage = stageReadyEvent.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(new ClassPathResource("/chart.fxml").getURL());
            fxmlLoader.setControllerFactory(aClass -> this.applicationContext.getBean(aClass));

            Parent load = fxmlLoader.load();
            stage.setScene(new Scene(load, 800, 600));
            // TODO: title
            stage.setTitle("Stocks");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

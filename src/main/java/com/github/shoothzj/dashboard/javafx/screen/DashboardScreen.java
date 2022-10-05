package com.github.shoothzj.dashboard.javafx.screen;

import com.github.shoothzj.dashboard.javafx.DashboardApplication;
import com.github.shoothzj.dashboard.javafx.constant.FxConst;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardScreen extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardApplication.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), FxConst.WIDTH, FxConst.HEIGHT);
        stage.setTitle("Welcome to Dashboard!");
        stage.setScene(scene);
        stage.show();
    }

}

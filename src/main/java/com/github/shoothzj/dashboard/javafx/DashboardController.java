package com.github.shoothzj.dashboard.javafx;

import com.github.shoothzj.dashboard.javafx.screen.ZookeeperListScreen;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    protected void onZooKeeperBtnClick() {
        Stage stage = new Stage();
        try {
            new ZookeeperListScreen().start(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onKubernetesBtnClick() {
    }

    @FXML
    protected void onBookkeeperBtnClick() {
    }


}
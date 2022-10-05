package com.github.shoothzj.dashboard.javafx.screen;

import com.github.shoothzj.dashboard.javafx.constant.FxConst;
import com.github.shoothzj.dashboard.javafx.dialog.CreateZooKeeperDialog;
import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import com.github.shoothzj.dashboard.javafx.service.ZooKeeperService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class ZookeeperListScreen extends Application {

    public ZookeeperListScreen() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button addButton = new Button("Add ZooKeeper Instance");
        addButton.setOnAction(__ -> showDialog());
        ListView<ZooKeeperInstance> listView = new ListView<>();
        for (ZooKeeperInstance zooKeeperInstance : ZooKeeperService.INSTANCE.getZooKeeperInstances()) {
            listView.getItems().add(zooKeeperInstance);
        }
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ZooKeeperInstance zooKeeperInstance = listView.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                try {
                    new ZooKeeperInteractiveScreen(zooKeeperInstance).start(stage);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        VBox box = new VBox(addButton, listView);
        Scene scene = new Scene(box, FxConst.WIDTH, FxConst.HEIGHT);
        stage.setTitle("ZooKeeper");
        stage.setScene(scene);
        stage.show();
    }

    private void showDialog() {
        CreateZooKeeperDialog createZooKeeperDialog = new CreateZooKeeperDialog();
        Optional<ZooKeeperInstance> zooKeeperInstanceOp = createZooKeeperDialog.showAndWait();
        zooKeeperInstanceOp.ifPresent(ZooKeeperService.INSTANCE::addZooKeeperInstance);
    }

}

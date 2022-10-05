package com.github.shoothzj.dashboard.javafx.screen;

import com.github.shoothzj.dashboard.javafx.connector.InteractiveListener;
import com.github.shoothzj.dashboard.javafx.connector.ZooKeeperConnectorBoot;
import com.github.shoothzj.dashboard.javafx.constant.FxConst;
import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ZooKeeperInteractiveScreen extends Application {

    private final ZooKeeperInstance zooKeeperInstance;

    public ZooKeeperInteractiveScreen(ZooKeeperInstance zooKeeperInstance) {
        this.zooKeeperInstance = zooKeeperInstance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea presentArea = new TextArea();
        presentArea.setEditable(false);
        presentArea.setPrefWidth(FxConst.PRESENT_WIDTH);
        presentArea.setPrefHeight(FxConst.PRESENT_HEIGHT);
        TextField commandField = new TextField();
        commandField.setEditable(true);
        Button sendButton = new Button("Send");
        HBox commandBox = new HBox(commandField, sendButton);
        VBox box = new VBox(presentArea, commandBox);
        Scene scene = new Scene(box, FxConst.WIDTH, FxConst.HEIGHT);
        primaryStage.setTitle("ZooKeeper Interactive Shell");
        primaryStage.setScene(scene);
        primaryStage.show();
        ZooKeeperConnectorBoot zooKeeperConnectorBoot = new ZooKeeperConnectorBoot(zooKeeperInstance, new InteractiveListener() {
            @Override
            public void onAppend(String text) {
                if (Platform.isFxApplicationThread()) {
                    presentArea.appendText(text);
                } else {
                    Platform.runLater(() -> presentArea.appendText(text));
                }
            }
        });
        sendButton.setOnAction(__ -> {
            zooKeeperConnectorBoot.send(commandField.getText() + "\n");
            if (Platform.isFxApplicationThread()) {
                commandField.clear();
            } else {
                Platform.runLater(commandField::clear);
            }
        });
        zooKeeperConnectorBoot.init();
    }
}

package com.github.shoothzj.dashboard.javafx.connector;

import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import lombok.SneakyThrows;

public class ZooKeeperConnectorBoot {

    private final ZooKeeperInstance zooKeeperInstance;

    private final InteractiveListener interactiveListener;

    private AbstractZooKeeperConnector zkConnector;

    public ZooKeeperConnectorBoot(ZooKeeperInstance zooKeeperInstance, InteractiveListener interactiveListener) {
        this.zooKeeperInstance = zooKeeperInstance;
        this.interactiveListener = interactiveListener;
    }

    @SneakyThrows
    public void init() {
        if (zooKeeperInstance.getProxyList().size() == 0) {
            this.zkConnector = new ZooKeeperShellConnector(zooKeeperInstance, this.interactiveListener);
        } else if (zooKeeperInstance.getProxyList().size() == 1) {
            this.zkConnector = new ZooKeeperSshProxyConnector(zooKeeperInstance, this.interactiveListener);
        } else if (zooKeeperInstance.getProxyList().size() == 2) {
            this.zkConnector = new ZooKeeperDoubleProxyConnector(zooKeeperInstance, this.interactiveListener);
        }
    }

    @SneakyThrows
    public void send(String cmd) {
        this.zkConnector.sendCmd(cmd);
    }

}

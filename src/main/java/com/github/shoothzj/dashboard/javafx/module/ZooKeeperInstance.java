package com.github.shoothzj.dashboard.javafx.module;

import lombok.Data;

import java.util.List;

@Data
public class ZooKeeperInstance {

    private String name;

    private String host;

    private int port;

    private List<Proxy> proxyList;

    private String script;

    public ZooKeeperInstance() {
    }
}

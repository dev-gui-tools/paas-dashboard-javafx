package com.github.shoothzj.dashboard.javafx.module;

import lombok.Data;

@Data
public class SshProxy {

    private String username;

    private String host;

    private int port;

    private String password;

    public SshProxy() {
    }

}

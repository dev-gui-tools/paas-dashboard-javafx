package com.github.shoothzj.dashboard.javafx.module;

import lombok.Data;

@Data
public class Proxy {

    private String proxyType;

    private SshProxy sshProxy;

    private KubernetesExecProxy kubernetesExecProxy;

    public Proxy() {
    }

}

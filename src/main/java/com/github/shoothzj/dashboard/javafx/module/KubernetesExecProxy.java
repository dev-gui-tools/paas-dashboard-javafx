package com.github.shoothzj.dashboard.javafx.module;

import lombok.Data;

@Data
public class KubernetesExecProxy {

    private String namespace;

    private String podName;

    public KubernetesExecProxy() {
    }

}

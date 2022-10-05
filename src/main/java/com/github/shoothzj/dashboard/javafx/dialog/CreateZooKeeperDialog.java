package com.github.shoothzj.dashboard.javafx.dialog;

import com.github.shoothzj.dashboard.javafx.module.KubernetesExecProxy;
import com.github.shoothzj.dashboard.javafx.module.Proxy;
import com.github.shoothzj.dashboard.javafx.module.SshProxy;
import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CreateZooKeeperDialog extends Dialog<ZooKeeperInstance> {

    public CreateZooKeeperDialog() {
        super();
        TextField nameLabel = new TextField("ZooKeeper Name Label");
        nameLabel.setEditable(false);
        TextField nameEditText = new TextField("");
        TextField zkHostLabel = new TextField("ZooKeeper Host");
        zkHostLabel.setEditable(false);
        TextField zkHostEditText = new TextField("localhost");
        TextField portLabel = new TextField("ZooKeeper Port");
        portLabel.setEditable(false);
        TextField portEditText = new TextField("2181");
        CheckBox sshCb = new CheckBox("Enable Ssh proxy");
        TextField sshHostLabel = new TextField("Ssh Host");
        sshHostLabel.setEditable(false);
        TextField sshHostEditText = new TextField("");
        TextField sshPortLabel = new TextField("Ssh Port");
        sshPortLabel.setEditable(false);
        TextField sshPortEditText = new TextField("");
        TextField sshUsernameLabel = new TextField("Ssh Username");
        sshUsernameLabel.setEditable(false);
        TextField sshUsernameEditText = new TextField("root");
        TextField sshPasswordLabel = new TextField("Ssh Password");
        sshPasswordLabel.setEditable(false);
        TextField sshPasswordEditText = new TextField("");
        CheckBox k8sCb = new CheckBox("Enable Kubernetes proxy");
        TextField k8sNamespaceLabel = new TextField("Kubernetes Namespace");
        k8sNamespaceLabel.setEditable(false);
        TextField k8sNamespaceEditText = new TextField("default");
        TextField k8sPodNameLabel = new TextField("Kubernetes PodName");
        k8sPodNameLabel.setEditable(false);
        TextField k8sPodNameEditText = new TextField("zookeeper-0");
        TextField scriptLabel = new TextField("ScriptLabel");
        scriptLabel.setEditable(false);
        TextField scriptEditText = new TextField("./zkCli.sh");
        VBox box = new VBox(nameLabel, nameEditText, zkHostLabel, zkHostEditText,
                portLabel, portEditText, sshCb, sshHostLabel, sshHostEditText,
                sshPortLabel, sshPortEditText, sshUsernameLabel, sshUsernameEditText,
                sshPasswordLabel, sshPasswordEditText, k8sCb, k8sNamespaceLabel,
                k8sNamespaceEditText, k8sPodNameLabel, k8sPodNameEditText,
                scriptLabel, scriptEditText);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        getDialogPane().setContent(box);
        setResultConverter(__ -> {
            ZooKeeperInstance zooKeeperInstance = new ZooKeeperInstance();
            zooKeeperInstance.setName(nameEditText.getText());
            zooKeeperInstance.setHost(zkHostEditText.getText());
            zooKeeperInstance.setPort(Integer.parseInt(portEditText.getText()));
            List<Proxy> proxyList = new ArrayList<>();
            if (sshCb.isSelected()) {
                SshProxy sshProxy = new SshProxy();
                sshProxy.setHost(sshHostEditText.getText());
                sshProxy.setPort(Integer.parseInt(sshPortEditText.getText()));
                sshProxy.setPassword(sshPasswordEditText.getText());
                Proxy proxy = new Proxy();
                proxy.setSshProxy(sshProxy);
                proxyList.add(proxy);
            }
            if (k8sCb.isSelected()) {
                KubernetesExecProxy k8sProxy = new KubernetesExecProxy();
                k8sProxy.setNamespace(k8sNamespaceEditText.getText());
                k8sProxy.setPodName(k8sPodNameEditText.getText());
                Proxy proxy = new Proxy();
                proxy.setKubernetesExecProxy(k8sProxy);
                proxyList.add(proxy);
            }
            zooKeeperInstance.setProxyList(proxyList);
            zooKeeperInstance.setScript(scriptEditText.getText());
            return zooKeeperInstance;
        });
    }

}

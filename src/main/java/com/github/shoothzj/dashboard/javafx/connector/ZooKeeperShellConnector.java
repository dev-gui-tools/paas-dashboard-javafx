package com.github.shoothzj.dashboard.javafx.connector;

import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import com.github.shoothzj.dashboard.javafx.util.CommonUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ZooKeeperShellConnector extends AbstractZooKeeperConnector {

    public ZooKeeperShellConnector(ZooKeeperInstance zooKeeperInstance, InteractiveListener interactiveListener) {
        super(zooKeeperInstance, interactiveListener);
        this.interactiveListener.onAppend("Connect to ZooKeeper" + System.lineSeparator());
    }

    @SneakyThrows
    public void connZk() {
        String command = String.format("%s -server %s:%d", instance.getScript(), instance.getHost(), instance.getPort());
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream inputStream = process.getInputStream();
        OutputStream outputStream = process.getOutputStream();
        String inputContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        while (true) {
            ZooKeeperShellConnector.this.interactiveListener.onAppend(inputContent);
            String poll = outputQueue.poll();
            if (poll == null) {
                CommonUtil.sleep(TimeUnit.MILLISECONDS, 300);
                continue;
            }
            outputStream.write(poll.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    protected void execCmd(String cmd) {

    }

}

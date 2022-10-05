package com.github.shoothzj.dashboard.javafx.connector;

import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import com.github.shoothzj.dashboard.javafx.util.CommonUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractZooKeeperConnector {

    protected final LinkedBlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();

    protected final ZooKeeperInstance instance;

    protected final InteractiveListener interactiveListener;

    public AbstractZooKeeperConnector(ZooKeeperInstance instance, InteractiveListener interactiveListener) {
        this.instance = instance;
        this.interactiveListener = interactiveListener;
        new Thread(this::interactive).start();
    }

    public void sendCmd(String cmd) {
        outputQueue.offer(cmd);
        this.interactiveListener.onAppend(cmd + System.lineSeparator());
    }

    private void interactive() {
        this.connZk();
        while (true) {
            String poll = outputQueue.poll();
            if (poll == null) {
                CommonUtil.sleep(TimeUnit.MILLISECONDS, 300);
                continue;
            }
            this.interactiveListener.onAppend(poll);
            this.execCmd(poll);
        }
    }

    protected abstract void connZk();

    protected abstract void execCmd(String cmd);

}

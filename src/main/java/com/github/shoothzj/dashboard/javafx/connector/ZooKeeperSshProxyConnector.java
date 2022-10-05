package com.github.shoothzj.dashboard.javafx.connector;

import com.github.shoothzj.dashboard.javafx.module.SshProxy;
import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import com.github.shoothzj.dashboard.javafx.util.CommonUtil;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZooKeeperSshProxyConnector extends AbstractZooKeeperConnector {

    private OutputStream writeCmdStream;

    private OutputStream printStream;

    public ZooKeeperSshProxyConnector(ZooKeeperInstance instance, InteractiveListener interactiveListener) {
        super(instance, interactiveListener);
    }

    @SneakyThrows
    @Override
    protected void connZk() {
        SshProxy sshProxy = instance.getProxyList().get(0).getSshProxy();
        Session session = new JSch().getSession(sshProxy.getUsername(), sshProxy.getHost(), sshProxy.getPort());
        session.setPassword(sshProxy.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelShell channel = (ChannelShell) session.openChannel("shell");
        channel.setPty(true);
        printStream = new ByteArrayOutputStream();
        channel.setOutputStream(printStream, true);
        this.interactiveListener.onAppend("Ssh success");
        writeCmdStream = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream((PipedOutputStream) writeCmdStream);
        channel.setInputStream(in, true);
        channel.connect();
        CommonUtil.sleep(TimeUnit.MILLISECONDS, 300);
        String command = String.format("%s -server %s:%d\n", instance.getScript(), instance.getHost(), instance.getPort());
        writeCmdStream.write(command.getBytes(StandardCharsets.UTF_8));
        writeCmdStream.flush();
        CommonUtil.sleep(TimeUnit.SECONDS, 2);
        this.interactiveListener.onAppend(printStream.toString());
    }

    @SneakyThrows
    @Override
    protected void execCmd(String cmd) {
        if (cmd == null) {
            CommonUtil.sleep(TimeUnit.MILLISECONDS, 300);
            return;
        }
        log.info("exec command {}", cmd);
        writeCmdStream.write(cmd.getBytes(StandardCharsets.UTF_8));
        writeCmdStream.flush();
        CommonUtil.sleep(TimeUnit.MILLISECONDS, 200);
        this.interactiveListener.onAppend(printStream.toString());
    }
}

package com.github.shoothzj.dashboard.javafx.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.shoothzj.dashboard.javafx.constant.PathConst;
import com.github.shoothzj.dashboard.javafx.module.ZooKeeperInstance;
import com.github.shoothzj.dashboard.javafx.util.FileTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ZooKeeperService {

    public static final ZooKeeperService INSTANCE = new ZooKeeperService();

    public boolean addZooKeeperInstance(ZooKeeperInstance zooKeeperInstance) {
        log.info("add zookeeper instance {}", zooKeeperInstance);
        List<ZooKeeperInstance> zooKeeperInstances = getZooKeeperInstances();
        if (zooKeeperInstances.stream().anyMatch(instance -> instance.getName().equals(zooKeeperInstance.getName()))) {
            return false;
        }
        zooKeeperInstances.add(zooKeeperInstance);
        writeToFile(zooKeeperInstances);
        return true;
    }

    public List<ZooKeeperInstance> getZooKeeperInstances() {
        String string = FileTool.file2String(PathConst.DASHBOARD_ZK_INSTANCES);
        List<ZooKeeperInstance> instances = JacksonService.toList(string, new TypeReference<>() {
        });
        return instances == null ? new ArrayList<>() : instances;
    }

    public void delZooKeeperInstance(String name) {
        List<ZooKeeperInstance> zooKeeperInstances = getZooKeeperInstances();
        List<ZooKeeperInstance> newList = zooKeeperInstances.stream().filter(instance -> !instance.getName().equals(name)).toList();
        writeToFile(newList);
    }

    private void writeToFile(List<ZooKeeperInstance> zooKeeperInstances) {
        String json = JacksonService.toJson(zooKeeperInstances);
        FileTool.string2File(json, PathConst.DASHBOARD_ZK_INSTANCES);
    }

}

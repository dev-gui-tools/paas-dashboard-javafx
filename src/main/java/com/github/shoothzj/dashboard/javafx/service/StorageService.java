package com.github.shoothzj.dashboard.javafx.service;

import com.github.shoothzj.dashboard.javafx.constant.PathConst;
import com.github.shoothzj.dashboard.javafx.util.FileTool;

public class StorageService {

    public static final StorageService INSTANCE = new StorageService();

    public void initDirectory() {
        FileTool.createDir(PathConst.DASHBOARD_DIR);
        FileTool.createDir(PathConst.DASHBOARD_VER_DIR);
        FileTool.createDir(PathConst.DASHBOARD_ZK_DIR);
        FileTool.createFile(PathConst.DASHBOARD_ZK_INSTANCES);
    }

}

package com.github.shoothzj.dashboard.javafx.constant;

import com.github.shoothzj.dashboard.javafx.util.EnvUtil;

import java.io.File;

public class PathConst {

    private static final String SEP = File.separator;

    public static final String USER_DIR = EnvUtil.getUserDir();

    public static final String DASHBOARD_DIR = USER_DIR + SEP + "dashboard";

    public static final String DASHBOARD_VER_DIR = DASHBOARD_DIR + SEP + "v1";

    public static final String DASHBOARD_ZK_DIR = DASHBOARD_VER_DIR + SEP + "zk";

    public static final String DASHBOARD_ZK_INSTANCES = DASHBOARD_ZK_DIR + SEP + "instances.json";

}

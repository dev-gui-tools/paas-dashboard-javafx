package com.github.shoothzj.dashboard.javafx;

import com.github.shoothzj.dashboard.javafx.screen.DashboardScreen;
import com.github.shoothzj.dashboard.javafx.service.StorageService;
import com.github.shoothzj.dashboard.javafx.util.StringUtil;
import javafx.application.Application;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

public class DashboardApplication {

    public static void main(String[] args) {
        String logLevel = System.getenv("LOG_LEVEL");
        if (StringUtil.isEmpty(logLevel)) {
            Configurator.setRootLevel(Level.INFO);
        } else {
            // todo LOG LEVEL
            Configurator.setRootLevel(Level.INFO);
        }
        StorageService.INSTANCE.initDirectory();
        Application.launch(DashboardScreen.class);
    }
}
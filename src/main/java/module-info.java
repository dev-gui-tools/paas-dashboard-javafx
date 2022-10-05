module com.github.shoothzj.dashboard.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    // ssh
    // jackson
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    // log
    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.slf4j;
    // commons
    requires org.apache.commons.io;
    requires org.apache.commons.lang3;

    requires static lombok;
    requires com.jcraft;

    opens com.github.shoothzj.dashboard.javafx to javafx.fxml;
    opens com.github.shoothzj.dashboard.javafx.module to com.fasterxml.jackson.databind;
    exports com.github.shoothzj.dashboard.javafx;
    exports com.github.shoothzj.dashboard.javafx.screen;
}
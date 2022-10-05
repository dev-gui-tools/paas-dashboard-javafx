package com.github.shoothzj.dashboard.javafx.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FileTool {

    @SneakyThrows
    public static void createDir(String dir) {
        new File(dir).mkdir();
    }

    @SneakyThrows
    public static void createFile(String file) {
        new File(file).createNewFile();
    }

    @SneakyThrows
    public static String file2String(String file) {
        return FileUtils.readFileToString(new File(file), StandardCharsets.UTF_8);
    }

    public static void string2File(String str, String file) {
        try {
            FileUtils.writeStringToFile(new File(file), str, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("write to file error ", e);
        }
    }

    public static void inputStream2File(InputStream inputStream, String file) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File(file));
        } catch (Exception e) {
            log.error("convert to file error, exception is ", e);
        }
    }

    public static void inputStream2File(InputStream inputStream, File file) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (Exception e) {
            log.error("convert to file error, exception is ", e);
        }
    }

}

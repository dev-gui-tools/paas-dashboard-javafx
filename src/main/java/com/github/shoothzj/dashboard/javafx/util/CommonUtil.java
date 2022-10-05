package com.github.shoothzj.dashboard.javafx.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CommonUtil {

    public CommonUtil() {
    }

    public static void sleep(TimeUnit timeUnit, int timeout) {
        try {
            switch (timeUnit) {
                case DAYS:
                    TimeUnit.DAYS.sleep(timeout);
                    break;
                case HOURS:
                    TimeUnit.HOURS.sleep(timeout);
                    break;
                case MINUTES:
                    TimeUnit.MINUTES.sleep(timeout);
                    break;
                case SECONDS:
                    TimeUnit.SECONDS.sleep(timeout);
                    break;
                case MILLISECONDS:
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    break;
                case MICROSECONDS:
                    TimeUnit.MICROSECONDS.sleep(timeout);
                    break;
                case NANOSECONDS:
                    TimeUnit.NANOSECONDS.sleep(timeout);
                    break;
                default:
                    log.info("do nothing");
            }
        } catch (InterruptedException var3) {
            // ignore
        }

    }
}

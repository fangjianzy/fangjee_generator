package com.fangjian.framework.elk;

import org.apache.log4j.Logger;

public class ApplicationElkLogs {
	private static final Logger LOGGER = Logger.getLogger(ApplicationElkLogs.class);
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            LOGGER.error("本地日志10---Info log [" + i + "].");
            Thread.sleep(500);
        }
    }
}

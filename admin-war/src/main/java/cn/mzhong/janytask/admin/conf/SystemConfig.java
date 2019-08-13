package cn.mzhong.janytask.admin.conf;

import java.io.File;

public abstract class SystemConfig {
    private SystemConfig() {
    }

    public final static String WORK_LOCATION = System.getProperty("user.home") + File.separator + ".JanyTask-Admin";
}

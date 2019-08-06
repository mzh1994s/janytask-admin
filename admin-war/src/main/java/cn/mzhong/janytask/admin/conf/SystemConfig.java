package cn.mzhong.janytask.admin.conf;

import java.io.File;

public abstract class SystemConfig {
    private SystemConfig() {
    }

    public final static String DATA_LOCATION = System.getProperty("user.home") + File.separator + ".janytask_admin";
}

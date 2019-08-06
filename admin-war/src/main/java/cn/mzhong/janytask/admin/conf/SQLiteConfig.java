package cn.mzhong.janytask.admin.conf;

import java.io.File;

public abstract class SQLiteConfig {
    private SQLiteConfig() {
    }

    public final static String DB_MAIN_LOCATION = SystemConfig.DATA_LOCATION + File.separator + "main.db";

}

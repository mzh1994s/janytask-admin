package cn.mzhong.janytask.admin.center;

import cn.mzhong.janytask.admin.center.mapper.main.InitMainMapper;
import cn.mzhong.janytask.admin.conf.SQLiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DatabaseInitializer {

    @Autowired
    InitMainMapper initMainMapper;

    private static boolean dbExists(String location) {
        File file = new File(location);
        return file.exists();
    }

    private static void createLocation(String location) {
        File file = new File(location);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isInited() {
        return dbExists(SQLiteConfig.DB_MAIN_LOCATION)
                && initMainMapper.isInited();
    }

    public void awaitInit() {
        createLocation(SQLiteConfig.DB_MAIN_LOCATION);
        initMainMapper.initTables();
    }
}

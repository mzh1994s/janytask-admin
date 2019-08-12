package cn.mzhong.janytask.admin.util;

import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
    private FileUtils() {
    }

    public static boolean exists(String file) {
        return new File(file).exists();
    }

    public static void createFile(String location) throws IOException {
        File file = new File(location);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new IOException("创建父级目录" + parent.getAbsolutePath() + "失败！");
                }
            }
            if (!file.createNewFile()) {
                throw new IOException("创建文件" + file.getAbsolutePath() + "失败！");
            }
        }
    }
}

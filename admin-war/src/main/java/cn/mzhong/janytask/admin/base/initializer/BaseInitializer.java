package cn.mzhong.janytask.admin.base.initializer;

import cn.mzhong.janytask.admin.base.mapper.main.UserMapper;
import cn.mzhong.janytask.admin.base.po.User;
import cn.mzhong.janytask.admin.response.ResponseException;
import cn.mzhong.janytask.admin.conf.SQLiteConfig;
import cn.mzhong.janytask.admin.conf.SystemConfig;
import cn.mzhong.janytask.admin.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 检查各项是否都已初始化，如果有一项未初始化，则整个初始化都将重新开始，并且会删除之前准备的所有数据。
 * 如果已经初始化，则初始化程序不能重复初始化，当调用初始化程序时会抛出重复初始化的异常。
 */
@Service
public class BaseInitializer {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 初始化数据目录，此操作会在USER.HOME目录下创建一个.janytask_admin文件夹。
     * 如果原来有这个文件夹，则会被删除后重建。
     */
    private void initializeWorkLocation() throws ResponseException {
        File workLocation = new File(SystemConfig.WORK_LOCATION);
        // 先删除原有工作空间
        if (workLocation.exists() && workLocation.isDirectory()) {
            if (!workLocation.delete()) {
                throw new ResponseException("初始化工作空间失败：无法删除原有的工作目录，请关闭应用后手动删除（位置：" + workLocation.getAbsolutePath() + "）！");
            }
        }
        // 创建工作空间目录
        if (!workLocation.mkdirs()) {
            throw new ResponseException("初始化工作空间失败：无法创建工作目录！");
        }
    }

    /**
     * 初始化主数据库
     */
    private void initializeMainDatabase(InitializeData prepareData) throws ResponseException {
        try {
            FileUtils.createFile(SQLiteConfig.DB_MAIN_LOCATION);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
        // User表
        userMapper.createTable();
        User admin = prepareData.getAdmin();
        // 密码加密后入库
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userMapper.insert(admin);
    }

    private boolean mainDBInitialized() {
        return FileUtils.exists(SQLiteConfig.DB_MAIN_LOCATION)
                // user表是否已经初始化
                && userMapper.isTableCreated() && userMapper.count() > 0;
    }

    /**
     * 已初始化的条件是：<br/>
     * 1、工作空间已创建<br/>
     * 2、主数据库已初始化<br/>
     *
     * @return
     */
    public boolean isInitialized() {
        return FileUtils.exists(SystemConfig.WORK_LOCATION)
                && mainDBInitialized();
    }

    public void awaitInitialize(InitializeData prepareData) throws ResponseException {
        // 如果已经初始化过，则不能再次初始化
        if (isInitialized()) {
            throw new ResponseException("不能重复初始化，如果需要重置管理应用程序，请删除" + SystemConfig.WORK_LOCATION);
        }
        // 初始化工作目录
        initializeWorkLocation();
        // 初始化主库
        initializeMainDatabase(prepareData);
    }
}

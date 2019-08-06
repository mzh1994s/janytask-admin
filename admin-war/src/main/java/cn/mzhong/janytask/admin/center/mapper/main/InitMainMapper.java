package cn.mzhong.janytask.admin.center.mapper.main;

public interface InitMainMapper {

    boolean isInited();

    /**
     * 初始化表
     */
    void initTables();
}

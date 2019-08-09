package cn.mzhong.janytask.admin.center;

import cn.mzhong.janytask.admin.base.po.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 初始化准备数据
 */
public class InitPrepareData implements Serializable {
    private static final long serialVersionUID = 9195984448397314223L;
    // 初始化管理员信息
    @Valid
    @NotNull(message = "管理员信息不能为null！")
    protected User admin;

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "InitPrepareData{" +
                "admin=" + admin +
                '}';
    }
}

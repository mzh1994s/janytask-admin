package cn.mzhong.janytask.admin.auth;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -9143417045593270143L;
    // 登录名
    @NotBlank(message = "登录名不能为空！")
    protected String username;
    // 登录密码
    @NotBlank(message = "登录名密码不能为空！")
    protected String password;
    // 验证码
    @NotBlank(message = "验证码不能为空！")
    protected String verifyCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}

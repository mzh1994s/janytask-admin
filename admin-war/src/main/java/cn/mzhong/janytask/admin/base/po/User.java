package cn.mzhong.janytask.admin.base.po;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -662193657145303863L;

    @NotBlank(message = "用户名不能为空！")
    protected String username;

    @NotBlank(message = "密码不能为空！")
    protected String password;
    protected String name;

    @NotBlank(message = "邮箱不能为空！")
    @Email(message = "邮箱格式不正确！")
    protected String email;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

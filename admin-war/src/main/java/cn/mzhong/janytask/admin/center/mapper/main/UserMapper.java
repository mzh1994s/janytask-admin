package cn.mzhong.janytask.admin.center.mapper.main;

import cn.mzhong.janytask.admin.base.po.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {

    @Insert(value = "insert into ja_admin(username,email,name,password) " +
            "values (#{username},#{email},#{name, jdbcType=VARCHAR},#{password})")
    public boolean insert(User user);
}

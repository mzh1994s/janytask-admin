package cn.mzhong.janytask.admin.center.mapper.main;

import cn.mzhong.janytask.admin.base.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    boolean isTableCreated();

    void createTable();

    @Insert(value = "insert into ja_user(username,email,name,password) " +
            "values (#{username},#{email},#{name, jdbcType=VARCHAR},#{password})")
    int insert(User user);

    @Select(value = "select count(*) from ja_user")
    int count();
}

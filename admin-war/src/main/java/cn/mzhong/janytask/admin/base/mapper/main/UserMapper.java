package cn.mzhong.janytask.admin.base.mapper.main;

import cn.mzhong.janytask.admin.base.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    boolean isTableCreated();

    void createTable();

    @Insert(value = "insert into ja_user(username,email,name,password) values (#{username},#{email},#{name, jdbcType=VARCHAR},#{password})")
    int insert(User user);

    @Select(value = "select u.username,u.email,u.name,u.password from ja_user u where u.username=#{username}")
    User selectOne(String username);

    @Select(value = "select count(*) from ja_user")
    int count();
}

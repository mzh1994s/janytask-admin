package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.base.mapper.main.UserMapper;
import cn.mzhong.janytask.admin.base.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User selectOne = userMapper.selectOne(username);
        if (selectOne == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }
        return new org.springframework.security.core.userdetails.User(
                selectOne.getUsername(),
                selectOne.getPassword(),
                new ArrayList<>()
        );
    }
}

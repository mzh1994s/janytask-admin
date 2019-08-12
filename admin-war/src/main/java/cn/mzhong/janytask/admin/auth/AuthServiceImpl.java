package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseException;
import cn.mzhong.janytask.admin.base.po.User;
import cn.mzhong.janytask.admin.base.mapper.main.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserMapper userMapper;

    public void login(String loginName, String password) throws ResponseException {
        User selectOne = userMapper.selectOne(loginName);
        if (selectOne == null) {
            throw new ResponseException("用户不存在！");
        }
        if (!Objects.equals(selectOne.getPassword(), password)) {
            throw new ResponseException("用户密码错误！");
        }
    }

    @Override
    public Set<String> getRules(String username) throws ResponseException {
        return null;
    }

    @Override
    public Set<String> getAuthorities(String username) throws ResponseException {
        return null;
    }
}

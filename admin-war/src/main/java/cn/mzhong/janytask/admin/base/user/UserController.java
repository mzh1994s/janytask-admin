package cn.mzhong.janytask.admin.base.user;

import cn.mzhong.janytask.admin.base.mapper.main.UserMapper;
import cn.mzhong.janytask.admin.base.po.User;
import cn.mzhong.janytask.admin.response.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/current")
    public ResponseInfo current(Authentication authentication) {
        String name = authentication.getName();
        User user = userMapper.selectOne(name);
        user.setPassword(null);
        return ResponseInfo.success(user);
    }
}

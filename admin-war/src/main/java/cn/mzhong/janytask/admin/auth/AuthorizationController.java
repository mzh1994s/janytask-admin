package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseException;
import cn.mzhong.janytask.admin.response.ResponseInfo;
import cn.mzhong.janytask.admin.base.verifycode.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RequestMapping(value = "/auth")
@RestController
public class AuthorizationController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @Autowired
    AuthService authService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseInfo login(@RequestBody @Valid LoginInfo loginInfo) throws ResponseException {
        String verifyCode = verifyCodeService.getVerifyCode(VerifyCodeService.LOGIN_KEY);
        if (!Objects.equals(loginInfo.getVerifyCode(), verifyCode)) {
            throw new ResponseException("输入验证码错误！");
        }
        // 登录成功将继续，否则抛出异常！
        authService.login(loginInfo.getUsername(), loginInfo.getPassword());
        // 登录成功
        return ResponseInfo.success();
    }
}

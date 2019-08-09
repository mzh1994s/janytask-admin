package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.base.ResponseInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/auth")
@RestController
public class AuthorizationController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseInfo login(@RequestBody @Valid LoginInfo loginInfo) {
        return ResponseInfo.success();
    }
}

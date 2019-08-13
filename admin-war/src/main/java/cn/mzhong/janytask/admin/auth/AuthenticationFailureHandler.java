package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseInfo;
import cn.mzhong.janytask.admin.response.ResponseUtils;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.write(response,
                ResponseInfo.error(-401, "登录失败：" + e.getLocalizedMessage()));
    }
}

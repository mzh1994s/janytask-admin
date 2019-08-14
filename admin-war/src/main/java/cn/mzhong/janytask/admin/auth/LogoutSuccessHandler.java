package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseInfo;
import cn.mzhong.janytask.admin.response.ResponseUtils;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseUtils.write(response, ResponseInfo.success());
    }
}

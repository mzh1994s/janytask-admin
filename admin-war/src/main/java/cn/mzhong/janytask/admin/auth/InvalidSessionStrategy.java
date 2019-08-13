package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseInfo;
import cn.mzhong.janytask.admin.response.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InvalidSessionStrategy implements org.springframework.security.web.session.InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResponseUtils.write(response,
                ResponseInfo.error(-401, "会话超时，请先重新登录！"));
    }
}

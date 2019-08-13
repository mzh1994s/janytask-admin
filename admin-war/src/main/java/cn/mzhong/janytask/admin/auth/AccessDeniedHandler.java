package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseInfo;
import cn.mzhong.janytask.admin.response.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.write(response, ResponseInfo.error(-403, e.getLocalizedMessage()));
    }
}

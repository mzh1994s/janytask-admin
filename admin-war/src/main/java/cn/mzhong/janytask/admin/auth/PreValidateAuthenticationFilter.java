package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.base.verifycode.VerifyCodeService;
import cn.mzhong.janytask.admin.response.ResponseException;
import cn.mzhong.janytask.admin.response.ResponseInfo;
import cn.mzhong.janytask.admin.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * UsernamePasswordAuthenticationFilter前置校验
 */
public class PreValidateAuthenticationFilter extends GenericFilterBean {

    private RequestMatcher requiresAuthenticationRequestMatcher;

    @Autowired
    VerifyCodeService verifyCodeService;

    public PreValidateAuthenticationFilter(String filterProcessesUrl) {
        this.requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(filterProcessesUrl);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("登录名不能为空，请输入用户名后重试！");
        }
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("登录密码不能为空，请输入登录密码后重试！");
        }
        String verifyCode = request.getParameter("verifyCode");
        if (StringUtils.isEmpty(verifyCode)) {
            throw new IllegalArgumentException("验证码不能为空，请输入验证码后重试！");
        }
        String savedVerifyCode;
        try {
            savedVerifyCode = verifyCodeService.getVerifyCode(session, VerifyCodeService.LOGIN_KEY);
        } catch (ResponseException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
        if (!Objects.equals(savedVerifyCode, verifyCode)) {
            throw new IllegalArgumentException("验证码错误，请重试！");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (requiresAuthenticationRequestMatcher.matches(httpServletRequest)) {
            try {
                this.doFilter(httpServletRequest, httpServletResponse);
            } catch (IllegalArgumentException e) {
                ResponseUtils.write(httpServletResponse, ResponseInfo.error(-401, e.getLocalizedMessage()));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}

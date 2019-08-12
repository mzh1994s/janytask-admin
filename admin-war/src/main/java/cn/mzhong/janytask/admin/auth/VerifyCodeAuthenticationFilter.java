package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.base.verifycode.VerifyCodeService;
import cn.mzhong.janytask.admin.response.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class VerifyCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    VerifyCodeService verifyCodeService;

    protected VerifyCodeAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String verifyCode;
        try {
            verifyCode = verifyCodeService.getVerifyCode(VerifyCodeService.LOGIN_KEY);
        } catch (ResponseException e) {
            throw new DisabledException(e.getLocalizedMessage());
        }
        String verifyCode1 = request.getParameter("verifyCode");
        if(!Objects.equals(verifyCode, verifyCode1)){
            throw new DisabledException("验证码不正确，请重试！");
        }
        return null;
    }
}

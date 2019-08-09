package cn.mzhong.janytask.admin.common;

import cn.mzhong.janytask.admin.base.ResponseException;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class VerifyCodeService extends ConfigurableCaptchaService {

    private final Logger Log = LoggerFactory.getLogger(VerifyCodeController.class);

    public final static String LOGIN_KEY = "login";

    private static final Map<String, String> keyMap = new HashMap<>();

    static {
        keyMap.put(LOGIN_KEY, "__verify_key_login");
    }

    @Autowired
    HttpSession httpSession;

    private String sessionKey(String key) throws ResponseException {
        String sessionKey = keyMap.get(key);
        if (sessionKey == null) {
            throw new ResponseException("验证码KEY不合法！");
        }
        return sessionKey;
    }

    public void writeVerifyCode(String key, OutputStream out) throws ResponseException {
        String sessionKey = sessionKey(key);
        try {
            httpSession.setAttribute(sessionKey, EncoderHelper.getChallangeAndWriteImage(this, "png", out));
        } catch (IOException e) {
            Log.error(e.getLocalizedMessage(), e);
            throw new ResponseException(e);
        }
    }

    public String getVerifyCode(String key) throws ResponseException {
        return (String) httpSession.getAttribute(sessionKey(key));
    }
}

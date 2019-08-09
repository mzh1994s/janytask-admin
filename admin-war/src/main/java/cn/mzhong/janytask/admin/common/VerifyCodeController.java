package cn.mzhong.janytask.admin.common;

import cn.mzhong.janytask.admin.base.ResponseException;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.service.CaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequestMapping(value = "verify_code")
@Controller
public class VerifyCodeController {

    Logger Log = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    VerifyCodeService verifyCodeService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "generate", params = {"key"})
    public ResponseEntity<byte[]> generateVerifyCode(String key) throws ResponseException {
        ByteArrayOutputStream captchaOutputStream = new ByteArrayOutputStream();
        verifyCodeService.writeVerifyCode(key, captchaOutputStream);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        httpHeaders.setCacheControl("no-cache");
        return new ResponseEntity<>(captchaOutputStream.toByteArray(), httpHeaders, HttpStatus.OK);
    }
}

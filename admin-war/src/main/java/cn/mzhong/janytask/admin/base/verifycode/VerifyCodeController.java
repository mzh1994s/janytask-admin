package cn.mzhong.janytask.admin.base.verifycode;

import cn.mzhong.janytask.admin.response.ResponseException;
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

@RequestMapping(value = "verify-code")
@Controller
public class VerifyCodeController {

    Logger Log = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    VerifyCodeService verifyCodeService;

    @RequestMapping(value = "generate", params = {"key"})
    public ResponseEntity<byte[]> generateVerifyCode(HttpSession httpSession, String key) throws ResponseException {
        ByteArrayOutputStream captchaOutputStream = new ByteArrayOutputStream();
        verifyCodeService.writeVerifyCode(httpSession, key, captchaOutputStream);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        httpHeaders.setCacheControl("no-cache");
        return new ResponseEntity<>(captchaOutputStream.toByteArray(), httpHeaders, HttpStatus.OK);
    }
}

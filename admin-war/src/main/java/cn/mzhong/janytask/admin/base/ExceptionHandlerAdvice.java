package cn.mzhong.janytask.admin.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    Logger Log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public RestInfo<String> handleException(Exception e) {
        Log.error(e.getLocalizedMessage(), e);
        return RestInfo.error(e.getLocalizedMessage());
    }
}

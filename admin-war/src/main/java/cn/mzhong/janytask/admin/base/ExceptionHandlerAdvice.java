package cn.mzhong.janytask.admin.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    Logger Log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    private String resolveErrorMessage(Errors errors) {
        List<ObjectError> allErrors = errors.getAllErrors();
        if (!allErrors.isEmpty()) {
            return allErrors.get(0).getDefaultMessage();
        }
        return "未知验证错误！";
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Log.error(e.getLocalizedMessage(), e);
        return RestInfo.error(resolveErrorMessage(e.getBindingResult()));
    }

    @ExceptionHandler(value = BindException.class)
    public RestInfo handleBindingException(BindException e) {
        Log.error(e.getLocalizedMessage(), e);
        return RestInfo.error(resolveErrorMessage(e.getBindingResult()));
    }

    @ExceptionHandler(value = Exception.class)
    public RestInfo handleException(Exception e) {
        Log.error(e.getLocalizedMessage(), e);
        return RestInfo.error(e.getLocalizedMessage());
    }
}

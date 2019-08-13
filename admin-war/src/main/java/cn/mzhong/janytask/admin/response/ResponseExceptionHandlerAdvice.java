package cn.mzhong.janytask.admin.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ResponseExceptionHandlerAdvice {

    Logger Log = LoggerFactory.getLogger(ResponseExceptionHandlerAdvice.class);

    private String resolveErrorMessage(Errors errors) {
        List<FieldError> fieldErrors = errors.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
        }
        return "未知验证错误！";
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Log.error(e.getLocalizedMessage(), e);
        return ResponseInfo.error(resolveErrorMessage(e.getBindingResult()));
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseInfo handleBindingException(BindException e) {
        Log.error(e.getLocalizedMessage(), e);
        return ResponseInfo.error(resolveErrorMessage(e.getBindingResult()));
    }

    @ExceptionHandler(value = ResponseException.class)
    public ResponseInfo handleResponseException(ResponseException e) {
        Log.error(e.getLocalizedMessage(), e);
        return ResponseInfo.error(e);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseInfo handleException(Exception e) {
        Log.error(e.getLocalizedMessage(), e);
        return ResponseInfo.error(e.getLocalizedMessage());
    }
}

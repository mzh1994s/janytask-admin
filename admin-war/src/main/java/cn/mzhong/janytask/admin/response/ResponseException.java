package cn.mzhong.janytask.admin.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseException extends Exception {
    private static final long serialVersionUID = -6862584915580889018L;
    protected int code = -1;
    protected List<String> details;

    public ResponseException() {
        super("未知异常！");
    }

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
        this.details = new ArrayList<>();
        StackTraceElement[] stackTrace = cause.getStackTrace();
        int index = stackTrace.length;
        while (index-- > 0) {
            this.details.add(stackTrace[index].toString());
        }
    }

    public ResponseException(Throwable cause) {
        this(cause.getLocalizedMessage(), cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}

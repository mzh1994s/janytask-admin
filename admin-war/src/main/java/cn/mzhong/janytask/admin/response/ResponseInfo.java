package cn.mzhong.janytask.admin.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ResponseInfo implements Serializable {
    private static final long serialVersionUID = -7165330465816791059L;
    protected int code;
    protected Serializable data;
    protected String msg;
    protected Set<String> details = new HashSet<>();

    private ResponseInfo() {
    }

    public ResponseInfo(int code, Serializable data) {
        this.code = code;
        this.data = data;
    }

    public ResponseInfo(int code, String message, Collection<String> details) {
        this.code = code;
        this.msg = message;
        this.details.addAll(details);
    }

    public int getCode() {
        return code;
    }

    public <T extends Serializable> T getData() {
        return (T) data;
    }

    public String getMsg() {
        return msg;
    }

    public Set<String> getDetails() {
        return details;
    }

    public ResponseInfo code(int code) {
        this.code = code;
        return this;
    }

    public ResponseInfo data(Serializable data) {
        this.data = data;
        return this;
    }

    public ResponseInfo msg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseInfo details(Collection<String> details) {
        this.details.addAll(details);
        return this;
    }

    public ResponseInfo detail(String detail) {
        this.details.add(detail);
        return this;
    }

    public static ResponseInfo success(Serializable data) {
        return new ResponseInfo(0, data);
    }

    public static ResponseInfo success() {
        return new ResponseInfo(0, null);
    }

    public static ResponseInfo error(String message) {
        ResponseInfo rInfo = new ResponseInfo();
        rInfo.code = -1;
        rInfo.msg = message;
        return rInfo;
    }

    public static ResponseInfo error(int code, String message) {
        ResponseInfo rInfo = new ResponseInfo();
        rInfo.code = code;
        rInfo.msg = message;
        return rInfo;
    }

    public static ResponseInfo error(ResponseException responseException) {
        ResponseInfo rInfo = new ResponseInfo();
        rInfo.code = responseException.getCode();
        rInfo.msg = responseException.getLocalizedMessage();
        if (responseException.getDetails() != null) {
            rInfo.details.addAll(responseException.getDetails());
        }
        return rInfo;
    }

    public static ResponseInfo error() {
        return error("未知异常");
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", details=" + details +
                '}';
    }
}

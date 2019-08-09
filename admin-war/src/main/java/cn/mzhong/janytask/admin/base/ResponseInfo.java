package cn.mzhong.janytask.admin.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("ALL")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseInfo implements Serializable {
    private static final long serialVersionUID = -7165330465816791059L;
    protected int code;
    protected Serializable data;
    protected String msg;
    protected List<String> details;

    public ResponseInfo() {
    }

    public ResponseInfo(int code, Serializable data) {
        this.code = code;
        this.data = data;
    }

    public ResponseInfo(int code, String message, List<String> details) {
        this.code = code;
        this.msg = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public <T extends Serializable> T getData() {
        return (T) data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
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

    public static ResponseInfo error(ResponseException responseException) {
        ResponseInfo rInfo = new ResponseInfo();
        rInfo.code = responseException.getCode();
        rInfo.msg = responseException.getLocalizedMessage();
        rInfo.details = responseException.getDetails();
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

package cn.mzhong.janytask.admin.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RestInfo<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -7165330465816791059L;
    protected int code;
    protected T data;
    protected String msg;
    protected List<String> details;

    public RestInfo() {
    }

    public RestInfo(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public RestInfo(int code, String message, List<String> details) {
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

    public Object getData() {
        return data;
    }

    public void setData(T data) {
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

    public static <T extends Serializable> RestInfo<T> success(T data) {
        return new RestInfo<T>(0, data);
    }

    public static <T extends Serializable> RestInfo<T> success() {
        return new RestInfo<T>(0, null);
    }

    public static RestInfo<String> error(String message) {
        RestInfo<String> rInfo = new RestInfo<>();
        rInfo.code = -1;
        rInfo.msg = message;
        return rInfo;
    }

    public static RestInfo<String> error() {
        return error("未知异常");
    }

    @Override
    public String toString() {
        return "RestInfo{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", details=" + details +
                '}';
    }
}

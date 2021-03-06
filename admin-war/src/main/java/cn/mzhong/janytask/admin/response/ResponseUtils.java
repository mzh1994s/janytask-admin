package cn.mzhong.janytask.admin.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 向客户端写状态和消息，消息在返回体中。
 */
public abstract class ResponseUtils {
    protected ResponseUtils() {
    }

    private final static ObjectMapper jsonWriter = new ObjectMapper();

    public static void write(HttpServletResponse response, ResponseInfo responseInfo) throws IOException {
        // 必须去掉，否则Spring Security无法实现会话保持
        // response.reset();
        response.setStatus(200);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(jsonWriter.writeValueAsString(responseInfo));
    }
}

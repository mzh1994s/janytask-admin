package cn.mzhong.janytask.admin.auth;

import cn.mzhong.janytask.admin.response.ResponseException;

import java.util.Set;

/**
 * 授权服务
 */
public interface AuthService {

    /**
     * 登录用户
     *
     * @param loginName
     * @param password
     * @return 权限列表
     * @throws ResponseException 登录失败
     */
    void login(String loginName, String password) throws ResponseException;

    /**
     * 获取用户的角色
     *
     * @param username
     * @return
     * @throws ResponseException
     */
    Set<String> getRules(String username) throws ResponseException;

    /**
     * 获取用户的权限
     *
     * @param username
     * @return
     * @throws ResponseException
     */
    Set<String> getAuthorities(String username) throws ResponseException;
}

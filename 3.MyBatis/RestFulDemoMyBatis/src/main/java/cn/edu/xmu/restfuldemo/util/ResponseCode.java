package cn.edu.xmu.restfuldemo.util;

/**
 * 返回的错误码
 * @author Ming Qiu
 */
public class ResponseCode {

    public static final Integer OK = 0;
    public static final String OK_MSG = "成功";

    public static final Integer AUTH_INVALID_JWT = 501;
    public static final String AUTH_INVALID_JWT_MSG = "JWT不合法";
    public static final Integer AUTH_JWT_EXPIRED = 502;
    public static final String AUTH_JWT_EXPIRED_MSG = "JWT过期";
    public static final Integer FIELD_NOTVALID = 503;
    public static final String FIELD_NOTVALID_MSG = "字段不合法";

    public static final Integer AUTH_INVALID_ACCOUNT = 700;
    public static final String AUTH_INVALID_ACCOUNT_MSG = "用户名不存在或者密码错误";
    public static final Integer AUTH_ID_NOTEXIST = 701;
    public static final String AUTH_ID_NOTEXIST_MSG = "登录用户id不存在";
    public static final Integer AUTH_USER_FORBIDDEN = 702;
    public static final String AUTH_USER_FORBIDDEN_MSG = "用户被禁止登录";
    public static final Integer AUTH_NEED_LOGIN = 704;
    public static final String AUTH_NEED_LOGIN_MSG = "需要先登录";
    public static final Integer AUTH_NOT_ALLOW = 705;
    public static final String AUTH_NOT_ALLOW_MSG = "无权限访问";

    public static final Integer USER_ID_NOTEXIST = 730;
    public static final String USER_ID_NOTEXIST_MSG = "操作的用户id不存在";
    public static final Integer USER_NAME_REGISTERED = 731;
    public static final String USER_NAME_REGISTERED_MSG = "用户名已被注册";
    public static final Integer EMAIL_REGISTERED = 732;
    public static final String EMAIL_REGISTERED_MSG = "邮箱已被注册";
    public static final Integer MOBILE_REGISTERED = 733;
    public static final String MOBILE_REGISTERED_MSG = "电话已被注册";
    public static final Integer RESOURCE_ID_NOTEXIST = 734;
    public static final String RESOURCE_ID_NOTEXIST_MSG = "操作的资源id不存在";
    public static final Integer RESOURCE_ID_WRONG = 735;
    public static final String RESOURCE_ID_WRONG_MSG = "操作的资源id不正确";
    public static final Integer ROLE_REGISTERED = 736;
    public static final String ROLE_REGISTERED_MSG = "角色名已存在";
    public static final Integer ROLE_ID_NOTEXIST = 737;
    public static final String ROLE_ID_NOTEXIST_MSG = "操作的角色id不存在";
    public static final Integer PRIVILEGE_ID_NOTEXIST = 738;
    public static final String PRIVILEGE_ID_NOTEXIST_MSG = "操作的权限id不存在";
    public static final Integer USERNAME_WRONG = 739;
    public static final String USERNAME_WRONG_MSG = "用户名不满足要求";
    public static final Integer PASSWORD_WRONG = 740;
    public static final String PASSWORD_WRONG_MSG = "密码不满足要求";
    public static final Integer PASSWORD_SAME = 741;
    public static final String PASSWORD_SAME_MSG = "不能与旧密码相同";
    public static final Integer URL_SAME = 742;
    public static final String URL_SAME_MSG = "权限url与RequestType重复";
    public static final Integer PRIVILEGE_SAME = 743;
    public static final String PRIVILEGE_SAME_MSG = "权限名称重复";
    public static final Integer PRIVILEGE_BIT_SAME = 744;
    public static final String PRIVILEGE_BIT_SAME_MSG = "权限位重复";

}

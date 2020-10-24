package cn.edu.xmu.restfuldemo.util;

/**
 * 返回的错误码
 * @author Ming Qiu
 */
public enum ResponseCode {
    OK(0,"成功"),
    /***************************************************
     *    系统级错误
     **************************************************/
    //所有需要登录才能访问的API都可能会返回以下错误
    AUTH_INVALID_JWT(501,"JWT不合法"),
    AUTH_JWT_EXPIRED(502,"JWT过期"),

    //以下错误码提示可以自行修改
    //--------------------------------------------
    FIELD_NOTVALID(503,"字段不合法"),
    //所有路径带id的API都可能返回此错误
    RESOURCE_ID_NOTEXIST(504,"操作的资源id不存在"),
    RESOURCE_ID_OUTSCOPE(505,"操作的资源id不是自己的对象"),
    //--------------------------------------------


    /***************************************************
     *    其他模块错误码
     **************************************************/
    ADDRESS_OUTLIMIT(601,"达到地址簿上限"),
    REGION_OBSOLETE(602,"地区已废弃"),
    ADVERTISEMENT_OUTLIMIT(603,"达到时段广告上限"),
    TIMESEG_CONFLICT(604,"时段冲突"),
    SHAREACT_CONFLICT(605,"分享活动时段冲突"),
    /***************************************************
     *    权限模块错误码
     **************************************************/
    AUTH_INVALID_ACCOUNT(700, "用户名不存在或者密码错误"),
    AUTH_ID_NOTEXIST(701,"登录用户id不存在"),
    AUTH_USER_FORBIDDEN(702,"用户被禁止登录"),
    AUTH_NEED_LOGIN(704, "需要先登录"),
    AUTH_NOT_ALLOW(705,"无权限访问"),
    USER_NAME_REGISTERED( 731,"用户名已被注册"),
    EMAIL_REGISTERED(732, "邮箱已被注册"),
    MOBILE_REGISTERED(733,"电话已被注册"),
    ROLE_REGISTERED(736, "角色名已存在"),
    PASSWORD_SAME(741,"不能与旧密码相同"),
    URL_SAME(742,"权限url与RequestType重复"),
    PRIVILEGE_SAME(743,"权限名称重复"),
    PRIVILEGE_BIT_SAME(744,"权限位重复"),
    EMAIL_WRONG(745,"与系统预留的邮箱不一致"),
    MOBILE_WRONG(746,"与系统预留的电话不一致");

    private int code;
    private String message;
    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }

}

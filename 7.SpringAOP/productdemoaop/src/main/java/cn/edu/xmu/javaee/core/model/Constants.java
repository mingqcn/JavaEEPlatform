//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.javaee.core.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 常量定义
 */
public interface Constants {
    /**
     * 时间格式定义
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * 默认结束时间
     */
    public static final LocalDateTime END_TIME = LocalDateTime.parse("2099-12-12T23:59:59", DATE_TIME_FORMATTER);

    public static final LocalDateTime BEGIN_TIME = LocalDateTime.parse("2000-12-12T23:59:59", DATE_TIME_FORMATTER);

    /**
     * 查询结果最大返回值
     */
    public static final int MAX_RETURN = 1000;

    /**
     * 平台用户的shopId
     */
    public static final Long PLATFORM = 0L;

    /**
     * 无商铺的后台管理用户
     */
    public static final Long NOSHOP = -1L;

    /**
     * 更新时ID不存在
     */
    public static final Long IDNOTEXIST = -1L;

}

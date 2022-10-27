//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther mingqiu
 * @date 2020/6/26 下午2:04
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {

    String departName() default "";

}

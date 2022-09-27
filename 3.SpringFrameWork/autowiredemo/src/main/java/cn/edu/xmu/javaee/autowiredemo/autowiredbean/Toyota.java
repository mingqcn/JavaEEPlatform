// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.autowiredbean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 8:55 2020/9/19
 **/
@Component
@Scope("prototype")
public class Toyota implements Car{
}

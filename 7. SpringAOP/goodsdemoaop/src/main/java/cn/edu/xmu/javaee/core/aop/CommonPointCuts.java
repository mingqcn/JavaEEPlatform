//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointCuts {

    @Pointcut("execution(public cn.edu.xmu.javaee.core.util.ReturnObject cn.edu.xmu.javaee.goodsdemo.controller..*.*(..))")
    public void controllers() {
    }

    @Pointcut("@annotation(cn.edu.xmu.javaee.core.aop.Audit)")
    public void auditAnnotation() {
    }
}

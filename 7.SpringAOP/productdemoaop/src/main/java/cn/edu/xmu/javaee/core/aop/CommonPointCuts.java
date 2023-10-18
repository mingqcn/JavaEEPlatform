//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointCuts {

    @Pointcut("execution(public cn.edu.xmu.javaee.core.model.ReturnObject cn.edu.xmu..controller..*.*(..))")
    public void controllers() {
    }

    @Pointcut("execution(public * cn.edu.xmu..dao..*.*(..))")
    public void daos(){

    }

    @Pointcut("@annotation(cn.edu.xmu.javaee.core.aop.Audit)")
    public void auditAnnotation() {
    }
}

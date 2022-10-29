//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.aop;

import cn.edu.xmu.javaee.core.util.BusinessException;
import cn.edu.xmu.javaee.core.util.JwtHelper;
import cn.edu.xmu.javaee.core.util.ReturnNo;
import cn.edu.xmu.javaee.core.util.ReturnObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @auther mingqiu
 * @date 2020/6/26 下午2:16
 *      modifiedBy Ming Qiu 2020/11/3 22:59
 *
 */
@Aspect
@Component
@Order(20)
public class AuditAspect {

    private final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @Getter
    @AllArgsConstructor
    class Token{
        private Long userId = null;
        private Long departId = null;
        private String userName=null;
        private Integer userLevel=null;
    }

    /**
     * @Audit的Around Advice
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("cn.edu.xmu.javaee.core.aop.CommonPointCuts.auditAnnotation()")
    public Object aroundAudit(JoinPoint joinPoint) throws  Throwable{

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader(JwtHelper.LOGIN_TOKEN_KEY);

        Token decryptToken = decryptToken(token);

        checkDepartId(request, method, decryptToken);

        Object[] args = joinPoint.getArgs();
        Annotation[][] annotations = method.getParameterAnnotations();
        putMethodParameter(decryptToken, args, annotations);

        Object obj = null;
        try {
            obj = ((ProceedingJoinPoint) joinPoint).proceed(args);
        } catch (Throwable e) {
            throw e;
        }
        return obj;
    }

    private void checkDepartId(HttpServletRequest request, Method method, Token decryptToken) throws BusinessException{
        //检验/shop的api中传入token是否和departId一致
        String pathInfo = request.getPathInfo();
        if(null ==pathInfo) {
            logger.info("aroundAudit : the api path is null");
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
        }

        String departName=null;
        Audit AuditAnnotation = method.getAnnotation(Audit.class);
        if(AuditAnnotation!=null){
            departName = ((Audit) AuditAnnotation).departName();
        }

        boolean flag=false;
        if(!"".equals(departName)) {
            logger.debug("aroundAudit: getPathInfo = {}", pathInfo);
            String paths[] = pathInfo.split("/");
            for (int i = 0; i < paths.length; i++) {
                //如果departId为0,可以操作所有的depart
                if (decryptToken.departId == 0) {
                    flag = true;
                    break;
                }
                if (paths[i].equals(departName)) {
                    if (i + 1 < paths.length) {
                        //找到路径上对应id 将其与string类型的departId比较
                        String pathId = paths[i + 1];
                        logger.debug("aroundAudit : did = {}" , pathId);
                        if (!pathId.equals(decryptToken.departId.toString())) {
                            logger.info("aroundAudit : 不匹配departId = {}", decryptToken.departId);
                            throw new BusinessException(ReturnNo.RESOURCE_ID_OUTSCOPE);
                        } else {
                            flag = true;
                            logger.debug("aroundAudit : success match Id!");
                        }
                    }
                    else {
                        flag = true;//这是没有departId的情况
                        break;
                    }
                }
            }
            if (flag == false) {
                logger.info("aroundAudit : 不匹配departId = {}", decryptToken.departId);
                throw new BusinessException(ReturnNo.RESOURCE_ID_OUTSCOPE);
            }
        }
        else {
            decryptToken.departId=null;
        }
    }

    private void putMethodParameter(Token token, Object[] args, Annotation[][] annotations) {
        for (int i = 0; i < annotations.length; i++) {
            Object param = args[i];
            Annotation[] paramAnn = annotations[i];
            if (paramAnn.length == 0){
                continue;
            }

            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为LoginUser.class
                if (annotation.annotationType().equals(LoginUser.class)) {
                    //校验该参数，验证一次退出该注解
                    args[i] = token.userId;
                }
                if (annotation.annotationType().equals(Depart.class)) {
                    //校验该参数，验证一次退出该注解
                    args[i] = token.departId;
                }
                if (annotation.annotationType().equals(LoginName.class)) {
                    //校验该参数，验证一次退出该注解
                    args[i] = token.userName;
                }
                if (annotation.annotationType().equals(UserLevel.class)) {
                    //校验该参数，验证一次退出该注解
                    args[i] = token.userLevel;
                }
            }
        }
    }

    private Token decryptToken(String token)  throws BusinessException{

        if (null == token){
            logger.info("aroundAudit : no token..");
            throw new BusinessException(ReturnNo.AUTH_NEED_LOGIN);
        }

        JwtHelper.UserAndDepart userAndDepart = new JwtHelper().verifyTokenAndGetClaims(token);

        if (null == userAndDepart) {
            logger.info("aroundAudit : invalid token..");
            throw new BusinessException(ReturnNo.AUTH_INVALID_JWT);
        }

        Long userId = userAndDepart.getUserId();
        Long departId = userAndDepart.getDepartId();
        String userName = userAndDepart.getUserName();
        Integer userLevel = userAndDepart.getUserLevel();
        if (userId == null) {
            logger.info("aroundAudit : userId is null");
            throw new BusinessException(ReturnNo.AUTH_NEED_LOGIN);
        }
        logger.debug("aroundAudit : userId = {}, departId={}, userName={}, userLevel={}", userId, departId, userName, userLevel);
        return  new Token(userId,departId,userName,userLevel);
    }
}

//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.aop;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.time.LocalDateTime;
import java.util.List;

import static cn.edu.xmu.javaee.core.model.Constants.BEGIN_TIME;
import static cn.edu.xmu.javaee.core.model.Constants.END_TIME;

@Aspect
@Component
@Order(10)
public class ControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Value("${oomall.core.page-size.max:1000}")
    private int max_page_size;

    @Value("${oomall.core.page-size.default:10}")
    private int default_page_size;

    /**
     * 所有返回值为ReturnObject的Controller
     *
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("cn.edu.xmu.javaee.core.aop.CommonPointCuts.controllers()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        ReturnObject retVal = null;

        MethodSignature ms = (MethodSignature) jp.getSignature();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String[] paramNames = ms.getParameterNames();
        logger.debug("doAround: method = {}, paramNames = {}", ms.getName(), paramNames);
        Object[] args = jp.getArgs();
        try {
            Object[] newArgs = checkPageTimeLimit(request, paramNames, args);
            Object obj = jp.proceed(newArgs);
            if (obj instanceof ReturnObject){
                retVal = (ReturnObject) obj;
            }else {
                retVal = new ReturnObject(obj);
            }
        } catch (BusinessException exception) {
            logger.info("doAround: BusinessException， errno = {}", exception.getErrno());
            retVal = new ReturnObject(exception.getErrno(), exception.getMessage());
        }

        ReturnNo code = retVal.getCode();
        logger.debug("doAround: jp = {}, code = {}", jp.getSignature().getName(), code);
        changeHttpStatus(code, response);

        return retVal;
    }

    /**
     * 根据code，修改reponse的HTTP Status code
     *
     * @param code
     * @param response
     */
    private void changeHttpStatus(ReturnNo code, HttpServletResponse response) {
        switch (code) {
            case CREATED:
                //201:
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;

            case RESOURCE_ID_NOTEXIST:
                // 404：资源不存在
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;

            case AUTH_INVALID_JWT:
            case AUTH_JWT_EXPIRED:
            case AUTH_NEED_LOGIN:
            case AUTH_ID_NOTEXIST:
            case AUTH_USER_FORBIDDEN:
            case AUTH_INVALID_ACCOUNT:
                // 401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                break;

            case INTERNAL_SERVER_ERR:
                // 500：数据库或其他严重错误
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                break;

            case FIELD_NOTVALID:
            case IMG_FORMAT_ERROR:
            case IMG_SIZE_EXCEED:
            case PARAMETER_MISSED:
            case INCONSISTENT_DATA:
                // 400
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                break;

            case RESOURCE_ID_OUTSCOPE:
            case FILE_NO_WRITE_PERMISSION:
            case AUTH_NO_RIGHT:
                // 403
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_OK);
        }
        response.setContentType("application/json;charset=UTF-8");
    }

    /**
     * 设置默认的page = 1和pageSize = 10
     * 防止客户端发过来pagesize过大的请求
     *
     * @param request
     * @param paramNames
     * @param args
     */
    private Object[] checkPageTimeLimit(HttpServletRequest request, String[] paramNames, Object[] args) {
        Integer page = 1, pageSize = default_page_size;

        if (request != null) {
            String pageString = request.getParameter("page");
            String pageSizeString = request.getParameter("pageSize");
            if (null != pageString  && !pageString.isEmpty() && pageString.matches("\\d+")) {
                page = Integer.valueOf(pageString);
                if (page <= 0) {
                    page = 1;
                }
            }

            if (null !=pageSizeString  && !pageSizeString.isEmpty() && pageSizeString.matches("\\d+")) {
                pageSize = Integer.valueOf(pageSizeString);
                if (pageSize <= 0 || pageSize > max_page_size) {
                    pageSize = default_page_size;
                }
            }
        }


        for (int i = 0; i < paramNames.length; i++) {
            logger.debug("checkPageTimeLimit: paramNames[{}] = {}", i, paramNames[i]);
            if (paramNames[i].equals("page")) {
                logger.debug("checkPageTimeLimit: set {} to {}",paramNames[i], page);
                args[i] = page;
                continue;
            }

            if (paramNames[i].equals("pageSize")) {
                logger.debug("checkPageTimeLimit: set {} to {}",paramNames[i], pageSize);
                args[i] = pageSize;
                continue;
            }

            if (paramNames[i].equals("beginTime") && (args[i] == null)){
                logger.debug("checkPageTimeLimit: set {} to {}",paramNames[i], BEGIN_TIME);
                args[i] = BEGIN_TIME;
                continue;
            }

            if (paramNames[i].equals("endTime") && (args[i] == null)){
                logger.debug("checkPageTimeLimit: set {} to {}",paramNames[i], END_TIME);
                args[i] = END_TIME;
            }
        }
        return args;
    }
}

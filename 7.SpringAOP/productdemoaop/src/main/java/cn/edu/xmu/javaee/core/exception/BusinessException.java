//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.exception;

import cn.edu.xmu.javaee.core.model.ReturnNo;

public class BusinessException extends RuntimeException{

    private ReturnNo errno;

    public BusinessException(ReturnNo errno, String message) {
        super(message);
        this.errno = errno;
    }

    public BusinessException(ReturnNo errno) {
        super(errno.getMessage());
        this.errno = errno;
    }

    public ReturnNo getErrno(){
        return this.errno;
    }
}

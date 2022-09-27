// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.cirualbean.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircualB {

    private CircualC c;

    @Autowired
    public CircualB(CircualC c) {
        this.c = c;
    }
}

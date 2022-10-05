// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.cirularbean.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularA {

    private CircularB b;

    @Autowired
    public void setB(CircularB b) {
        this.b = b;
    }
}

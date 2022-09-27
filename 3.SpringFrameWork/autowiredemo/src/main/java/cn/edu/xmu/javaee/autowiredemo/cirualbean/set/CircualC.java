//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.cirualbean.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircualC {

    private CircualA a;

    @Autowired
    public void setA(CircualA a) {
        this.a = a;
    }
}

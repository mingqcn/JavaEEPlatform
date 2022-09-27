// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.autowiredbean;

/**
 * @author: Ming Qiu
 * @date: Created in 17:06 2020/7/31
 **/
public class Boss {

    private Car car;
    private Office office;

    public Boss(Car toyota, Office office){
        this.car = toyota;
        this.office = office;
    }
}

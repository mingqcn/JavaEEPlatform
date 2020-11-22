package cn.edu.xmu.webfluxdemo.model.vo;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/1
 *     ModifiedBy Ming Qiu 2020/11/7
 **/
public interface VoObject {

    /**
     * 创建Vo对象
     * @return Vo对象
     */
    public Object createVo();

    /**
     * 创建简单Vo对象
     * @return 简单Vo对象
     */
    public Object createSimpleVo();

}

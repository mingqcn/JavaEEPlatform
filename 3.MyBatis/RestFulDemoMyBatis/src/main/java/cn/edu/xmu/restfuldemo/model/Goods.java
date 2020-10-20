package cn.edu.xmu.restfuldemo.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Goods implements VoObject {
    /**
     * 商品状态
     */
    public enum Status {
        UNPUBLISHED(0,"未发布"),
        PUBLISHED(1,"发布"),
        DELETED(2,"废弃");

        private static final Map<Integer, Status> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap();
            for (Status enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        Status(int code, String description) {
            this.code=code;
            this.description=description;
        }

        public static Status getStatusByCode(Integer code){
            return stateMap.get(code);
        }

        public Integer getCode(){
            return code;
        }

        public String getDescription() {return description;}

    }
    /**
     * 代理对象
     */
    private GoodsPo goodsPo;

    /**
     * 构造函数
     */
    public Goods() {
        this.goodsPo = new GoodsPo();
    }

    /**
     * 构造函数
     */
    public Goods(GoodsPo goodsPo) {
        this.goodsPo = goodsPo;
    }

    /**
     * 由Goods对象创建Vo对象
     */
    @Override
    public Object createVo(){
        return new GoodsRetVo(this);
    }

    /**
     * 获得内部的代理对象
     * @return GoodsPo对象
     */
    public GoodsPo gotGoodsPo(){
        return this.goodsPo;
    }

    public Integer getId() {
        return goodsPo.getId();
    }

    public String getGoodsSn() {
        return goodsPo.getGoodsSn();
    }

    public String getName() {
        return goodsPo.getName();
    }

    public Integer getCategoryId() {
        return goodsPo.getCategoryId();
    }

    public void setCategoryId(Integer categoryId) {
        goodsPo.setCategoryId(categoryId);
    }

    public void setBrandId(Integer brandId) {
        goodsPo.setBrandId(brandId);
    }

    public void setId(Integer id) {
        goodsPo.setId(id);
    }

    public void setPicUrl(String picUrl) {
        goodsPo.setPicUrl(picUrl);
    }

    public Integer getBrandId() {
        return goodsPo.getBrandId();
    }

    public String getBrief() {
        return goodsPo.getBrief();
    }

    public String getPicUrl() {
        return goodsPo.getPicUrl();
    }

    public void setUnit(String unit) {
        goodsPo.setUnit(unit);
    }

    public void setState(Status state) {
        goodsPo.setState(state.getCode());
    }

    public String getUnit() {
        return goodsPo.getUnit();
    }

    public LocalDateTime getUpdateTime() {
        return goodsPo.getUpdateTime();
    }

    public Status getState() {
        return Status.getStatusByCode(goodsPo.getState());
    }

    public String getSpecList() {
        return goodsPo.getSpecList();
    }

    public void setName(String name) {
        goodsPo.setName(name);
    }

    public void setAddTime(LocalDateTime addTime) {
        goodsPo.setAddTime(addTime);
    }

    public Integer getModiUser() {
        return goodsPo.getModiUser();
    }

    public void setGoodsSn(String goodsSn) {
        goodsPo.setGoodsSn(goodsSn);
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        goodsPo.setUpdateTime(updateTime);
    }

    public void setBrief(String brief) {
        goodsPo.setBrief(brief);
    }

    public void setModiUser(Integer modiUser) {
        goodsPo.setModiUser(modiUser);
    }

    public LocalDateTime getAddTime() {
        return goodsPo.getAddTime();
    }

    public void setSpecList(String specList) {
        goodsPo.setSpecList(specList);
    }

    public boolean canEqual(Object other) {
        return goodsPo.canEqual(other);
    }

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        return goodsPo.hashCode();
    }

    @Override
    public String toString() {
        return goodsPo.toString();
    }

    @Override
    public boolean equals(Object o) {
        return goodsPo.equals(((Goods)o).goodsPo);
    }

}
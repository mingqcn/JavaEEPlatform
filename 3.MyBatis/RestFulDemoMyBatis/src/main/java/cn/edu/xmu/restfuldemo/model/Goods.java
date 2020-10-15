package cn.edu.xmu.restfuldemo.model;

import cn.edu.xmu.restfuldemo.util.JacksonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(description = "商品")
public class Goods {

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
     * 获得内部的代理对象
     * @return GoodsPo对象
     */
    public GoodsPo gotGoodsPo(){
        return this.goodsPo;
    }

    @ApiModelProperty(value = "商品id")
    public Integer getId() {
        return goodsPo.getId();
    }

    @ApiModelProperty(value = "商品编号")
    public String getGoodsSn() {
        return goodsPo.getGoodsSn();
    }

    @ApiModelProperty(value = "商品名称")
    public String getName() {
        return goodsPo.getName();
    }

    @ApiModelProperty(value = "类别id")
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

    @ApiModelProperty(value = "品牌id")
    public Integer getBrandId() {
        return goodsPo.getBrandId();
    }

    @ApiModelProperty(value = "商品描述")
    public String getBrief() {
        return goodsPo.getBrief();
    }

    @ApiModelProperty(value = "图片url")
    public String getPicUrl() {
        return goodsPo.getPicUrl();
    }

    public void setUnit(String unit) {
        goodsPo.setUnit(unit);
    }

    public void setBeOnsale(Boolean beOnSale) {
        goodsPo.setBeOnsale(beOnSale);
    }

    public String getUnit() {
        return goodsPo.getUnit();
    }

    @ApiModelProperty(value = "最后一次修改时间")
    public LocalDateTime getUpdateTime() {
        return goodsPo.getUpdateTime();
    }

    @ApiModelProperty(value ="是否上架")
    public Boolean getBeOnsale() {
        return goodsPo.getBeOnsale();
    }

    @ApiModelProperty(value = "商品可选规格")
    public String getSpecList() {
        return goodsPo.getSpecList();
    }

    public void setName(String name) {
        goodsPo.setName(name);
    }

    public void setAddTime(LocalDateTime addTime) {
        goodsPo.setAddTime(addTime);
    }

    @ApiModelProperty(value = "修改者")
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

    @ApiModelProperty(value = "创建时间")
    public LocalDateTime getAddTime() {
        return goodsPo.getAddTime();
    }

    public void setSpecList(String specList) {
        goodsPo.setSpecList(specList);
    }

    public boolean canEqual(Object other) {
        return goodsPo.canEqual(other);
    }

    @ApiModelProperty(value = "商品规格")
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
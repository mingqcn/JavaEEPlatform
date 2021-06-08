package cn.edu.xmu.restfuldemo.util;

import cn.edu.xmu.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.restfuldemo.controller.vo.ProductVo;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.model.SpecItem;
import cn.edu.xmu.restfuldemo.model.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ming Qiu
 * @date: Created in 7:58 2020/10/4
 **/
public class GoodsFactory {

    private static GoodsFactory instance =  null;

    public static GoodsFactory getInstance(){
        if (instance == null){
            synchronized (GoodsFactory.class){
                if (instance == null){
                    instance = new GoodsFactory();
                }
            }
        }
        return instance;
    }

    public GoodsVo createGoodsVo() {
        GoodsVo g = new GoodsVo();
        g.setName("红米4X");
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");
        g.setCategoryId(11);
        g.setBrandId(12);

        g.setSpecList(createSpecList());
        g.setProductList(createProductVoList());
        return g;

    }

    public GoodsVo createNoNameGoodVo() {
        GoodsVo g = new GoodsVo();
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");
        g.setCategoryId(11);
        g.setBrandId(12);

        g.setSpecList(createSpecList());
        g.setProductList(createProductVoList());
        return g;
    }

    public Goods createGoods(Integer id) {
        Goods g = new Goods();
        g.setId(id);
        g.setGoodsSn("111111");
        g.setName("红米4X");
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");

        g.setSpecList(createSpecList());
        return g;
    }

    public Goods createGoods(String name) {
        Goods g = new Goods();
        g.setId(1);
        g.setGoodsSn("111111");
        g.setName(name);
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");

        g.setSpecList(createSpecList());
        return g;
    }

    private List<Specification> createSpecList() {
        List<Specification> specificationList = new ArrayList<>(3);

        Specification spec = new Specification();
        spec.setSpecName("颜色");
        List<SpecItem> specItemList = new ArrayList<>(3);
        SpecItem item = new SpecItem();
        item.setId("1");
        item.setContent("香槟金");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("樱花粉");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("3");
        item.setContent("磨砂黑");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec = new Specification();
        spec.setSpecName("内存");
        specItemList = new ArrayList<>(2);
        item = new SpecItem();
        item.setId("1");
        item.setContent("2G");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("3G");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec = new Specification();
        spec.setSpecName("机身存储");
        specItemList = new ArrayList<>(2);
        item = new SpecItem();
        item.setId("1");
        item.setContent("16G");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("32G");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        return specificationList;
    }

    private List<ProductVo> createProductVoList() {
        List<ProductVo> productVoList = new ArrayList<>(12);
        ProductVo productVo = new ProductVo();
        productVo.setProductSn("1-1-1");
        productVo.setDesc("香槟金2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-1-2");
        productVo.setDesc("香槟金2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-1");
        productVo.setDesc("香槟金3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-2");
        productVo.setDesc("香槟金3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-1");
        productVo.setDesc("樱花粉2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-2");
        productVo.setDesc("樱花粉2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-1");
        productVo.setDesc("樱花粉3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-2");
        productVo.setDesc("樱花粉3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-1");
        productVo.setDesc("磨砂黑2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-2");
        productVo.setDesc("磨砂黑2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-1");
        productVo.setDesc("磨砂黑3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-2");
        productVo.setDesc("磨砂黑3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);
        return productVoList;
    }
}

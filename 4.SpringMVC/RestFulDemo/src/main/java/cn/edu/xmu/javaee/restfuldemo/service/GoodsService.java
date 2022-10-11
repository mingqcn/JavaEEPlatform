package cn.edu.xmu.javaee.restfuldemo.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.javaee.restfuldemo.service.bo.*;

@Service
public class GoodsService {

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id 商品id
     * @return 商品对象
     */
    public Goods findById(Integer id) {
        Goods g = createGoods(id);
        return g;
    }

    /**
     * 用商品名称搜索商品
     *
     * @param name 商品名称
     * @return 商品对象
     */
    public Goods searchByName(String name) {
        Goods g = createGoods(name);
        return g;
    }

    /**
     * 新增商品
     * @param goods 新商品信息
     * @return 新商品
     */
    public Goods createGoods(Goods goods) {
        goods.setId(1);
        goods.setGoodsSn("11111");
        return goods;
    }


    /**
     * 修改商品
     * @param id 修改的商品对象id
     * @param goods 修改商品信息
     * @return 修改后是否成功
     */
    public boolean modifyGoods(Integer id, Goods goods) {
        return true;
    }

     /** 删除商品
     * @param id 商品id
     * @return 删除是否成功
     */
    public boolean delGoods(Integer id) {
        return true;
    }

    private Goods createGoods(Integer id) {
        Goods g = new Goods();
        g.setId(id);
        g.setGoodsSn("111111");
        g.setName("红米4X");
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");

        List<Specification> specificationList = new ArrayList<>(4);

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

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);
        g.setSpecList(specificationList);
        return g;
    }

    private Goods createGoods(String name) {
        Goods g = new Goods();
        g.setId(1);
        g.setGoodsSn("111111");
        g.setName(name);
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");

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

        g.setSpecList(specificationList);
        return g;
    }

}

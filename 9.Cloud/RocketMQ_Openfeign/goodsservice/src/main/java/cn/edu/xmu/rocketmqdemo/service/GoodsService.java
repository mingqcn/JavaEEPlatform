package cn.edu.xmu.rocketmqdemo.service;

import cn.edu.xmu.rocketmqdemo.model.Goods;
import cn.edu.xmu.rocketmqdemo.model.SpecItem;
import cn.edu.xmu.rocketmqdemo.model.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

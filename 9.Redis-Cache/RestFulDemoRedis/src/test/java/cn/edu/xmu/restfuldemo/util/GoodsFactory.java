package cn.edu.xmu.restfuldemo.util;

import cn.edu.xmu.restfuldemo.model.GoodsVo;
import cn.edu.xmu.restfuldemo.model.ProductVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming Qiu
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

    private String createSpecList() {
        return "[{\"specName\":\"颜色\",\"specItemList\":[{\"id\":\"1\",\"content\":\"香槟金\"},{\"id\":\"2\",\"content\":\"樱花粉\"},{\"id\":\"3\",\"content\":\"磨砂黑\"}]},{\"specName\":\"内存\",\"specItemList\":[{\"id\":\"1\",\"content\":\"2G\"},{\"id\":\"2\",\"content\":\"3G\"}]},{\"specName\":\"机身存储\",\"specItemList\":[{\"id\":\"1\",\"content\":\"16G\"},{\"id\":\"2\",\"content\":\"32G\"}]}]";
    }

    private List<ProductVo> createProductVoList() {
        List<ProductVo> productVoList = new ArrayList<>(12);
        ProductVo productVo = new ProductVo();
        productVo.setProductSn("1-1-1");
        productVo.setName("香槟金2G内存16G存储");
        productVo.setOriginalPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-1-2");
        productVo.setName("香槟金2G内存32G存储");
        productVo.setOriginalPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-1");
        productVo.setName("香槟金3G内存16G存储");
        productVo.setOriginalPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-2");
        productVo.setName("香槟金3G内存32G存储");
        productVo.setOriginalPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-1");
        productVo.setName("樱花粉2G内存16G存储");
        productVo.setOriginalPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-2");
        productVo.setName("樱花粉2G内存32G存储");
        productVo.setOriginalPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-1");
        productVo.setName("樱花粉3G内存16G存储");
        productVo.setOriginalPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-2");
        productVo.setName("樱花粉3G内存32G存储");
        productVo.setOriginalPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-1");
        productVo.setName("磨砂黑2G内存16G存储");
        productVo.setOriginalPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-2");
        productVo.setName("磨砂黑2G内存32G存储");
        productVo.setOriginalPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-1");
        productVo.setName("磨砂黑3G内存16G存储");
        productVo.setOriginalPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-2");
        productVo.setName("磨砂黑3G内存32G存储");
        productVo.setOriginalPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);
        return productVoList;
    }
}

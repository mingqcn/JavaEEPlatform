package cn.edu.xmu.restfuldemo.service;

import cn.edu.xmu.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.restfuldemo.controller.vo.ProductVo;
import cn.edu.xmu.restfuldemo.dao.GoodsDao;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.model.GoodsPo;
import cn.edu.xmu.restfuldemo.model.Product;
import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id 商品id
     * @return 商品对象
     */
    public ReturnObject<Goods> findById(Integer id) {
        GoodsPo queryObj = new GoodsPo();
        queryObj.setId(id);
        ReturnObject<Goods> retGoods = null;
        ReturnObject<List<Goods>> returnObject = goodsDao.findGoods(queryObj, true);
        if (returnObject.getErrno().equals(ResponseCode.OK)) {
            if (returnObject.getData().size() == 1) {
                retGoods = new ReturnObject<>(returnObject.getData().get(0));
            }
        }else{
            retGoods = new ReturnObject<>(returnObject.getErrno(), returnObject.getErrmsg());
        }
        return retGoods;
    }

    /**
     * 用商品名称搜索商品
     *
     * @param name 商品名称
     * @return 商品对象
     */
    public ReturnObject<Goods> searchByName(String name) {
        GoodsPo queryObj = new GoodsPo();
        queryObj.setName(name);
        ReturnObject<Goods> retGoods = null;
        ReturnObject<List<Goods>> returnObject = goodsDao.findGoods(queryObj, false);
        if (returnObject.getErrno().equals(ResponseCode.OK)) {
            if (returnObject.getData().size() == 1) {
                retGoods = new ReturnObject<>(returnObject.getData().get(0));
            }
        }else{
            retGoods = new ReturnObject<>(returnObject.getErrno(), returnObject.getErrmsg());
        }
        return retGoods;
    }

    /**
     * 新增商品
     * @param goodsVo 新商品信息
     * @return 新商品
     */
    @Transactional
    public ReturnObject<Goods> createGoods(GoodsVo goodsVo) {
        Goods goods = goodsVo.createGoods();
        ReturnObject<Goods> retObj = goodsDao.createGoods(goods);
        return retObj;
    }


    /**
     * 修改商品
     * @param id 修改的商品对象id
     * @param goodsVo 修改商品信息
     * @return 修改后是否成功
     */
    @Transactional
    public ReturnObject<Object> modifyGoods(Integer id, GoodsVo goodsVo, Integer modiUser) {
        Goods goods = goodsVo.createGoods();
        goods.setId(id);
        goods.setModiUser(modiUser);
        return goodsDao.modiGoods(goods);
    }

    /** 删除商品
     * @param id 商品id
     * @return 删除是否成功
     */
    @Transactional
    public ReturnObject<Object> delGoods(Integer id) {
        return goodsDao.deleteGoods(id);
    }
}

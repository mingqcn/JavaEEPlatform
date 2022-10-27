//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.dao;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.OnSale;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.OnSalePoMapper;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.OnSalePoExample;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static cn.edu.xmu.javaee.core.util.Common.createListObj;

@Repository
public class OnSaleDao {

    private Logger logger = LoggerFactory.getLogger(OnSaleDao.class);

    private OnSalePoMapper onSalePoMapper;

    @Autowired
    public OnSaleDao(OnSalePoMapper onSalePoMapper) {
        this.onSalePoMapper = onSalePoMapper;
    }

    /**
     * 获得货品的最近的价格和库存
     *
     * @param productId 货品对象
     * @return 规格对象
     */
    public List<OnSale> getLatestValidOnSale(Long productId) throws DataAccessException {
        PageHelper.startPage(1, 1);
        OnSalePoExample example = new OnSalePoExample();
        example.setOrderByClause("end_time DESC");
        OnSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andBeginTimeLessThanOrEqualTo(LocalDateTime.now());
        List<OnSalePo> onsalePoList = onSalePoMapper.selectByExample(example);
        return createListObj(onsalePoList, OnSale.class);
    }

    /**
     * 获得货品的最近的价格和库存
     *
     * @param productId 货品对象
     * @return 规格对象
     */
    public List<OnSale> getAllOnSale(Long productId, int page, int pageSize) throws DataAccessException {
        PageHelper.startPage(page, pageSize);
        OnSalePoExample example = new OnSalePoExample();
        example.setOrderByClause("end_time DESC");
        OnSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<OnSalePo> onsalePoList = onSalePoMapper.selectByExample(example);
        return createListObj(onsalePoList, OnSale.class);
    }

}

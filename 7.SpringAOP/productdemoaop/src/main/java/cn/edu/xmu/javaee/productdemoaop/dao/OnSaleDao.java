//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.productdemoaop.dao;

import cn.edu.xmu.javaee.productdemoaop.dao.bo.OnSale;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.OnSalePoMapper;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePoExample;
import cn.edu.xmu.javaee.productdemoaop.util.CloneFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OnSaleDao {

    private final static Logger logger = LoggerFactory.getLogger(OnSaleDao.class);

    private OnSalePoMapper onSalePoMapper;

    @Autowired
    public OnSaleDao(OnSalePoMapper onSalePoMapper) {
        this.onSalePoMapper = onSalePoMapper;
    }

    /**
     * 获得货品的最近的价格和库存
     * @param productId 货品对象
     * @return 规格对象
     */
    public List<OnSale> getLatestOnSale(Long productId) throws DataAccessException {
        LocalDateTime now = LocalDateTime.now();
        OnSalePoExample example = new OnSalePoExample();
        example.setOrderByClause("end_time DESC");
        OnSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andBeginTimeLessThanOrEqualTo(now);
        criteria.andEndTimeGreaterThanOrEqualTo(now);
        List<OnSalePo> onsalePoList = onSalePoMapper.selectByExample(example);
        return onsalePoList.stream().map(po-> CloneFactory.copy(new OnSale(), po)).collect(Collectors.toList());
    }
}

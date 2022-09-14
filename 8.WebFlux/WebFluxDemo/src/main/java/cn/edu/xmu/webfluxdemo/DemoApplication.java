package cn.edu.xmu.webfluxdemo;

import cn.edu.xmu.webfluxdemo.mapper.FlashSaleItemPoMapper;
import cn.edu.xmu.webfluxdemo.mapper.GoodsSkuPoMapper;
import cn.edu.xmu.webfluxdemo.model.bo.FlashSaleItem;
import cn.edu.xmu.webfluxdemo.model.po.FlashSaleItemPo;
import cn.edu.xmu.webfluxdemo.model.po.FlashSaleItemPoExample;
import cn.edu.xmu.webfluxdemo.model.po.GoodsSkuPo;
import cn.edu.xmu.webfluxdemo.model.po.GoodsSkuPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming Qiu
 */
@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Autowired
	private FlashSaleItemPoMapper flashSaleItemPoMapper;

	@Autowired
	private GoodsSkuPoMapper goodsSkuPoMapper;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		FlashSaleItemPoExample example = new FlashSaleItemPoExample();
		FlashSaleItemPoExample.Criteria criteria = example.createCriteria();
		criteria.andSaleIdEqualTo(Long.valueOf(1));
		List<FlashSaleItemPo> flashSaleItemPoList = flashSaleItemPoMapper.selectByExample(example);
		String key = "1";
		for (FlashSaleItemPo itemPo: flashSaleItemPoList){
			Long productId = itemPo.getGoodsSkuId();
			GoodsSkuPo skuPo = goodsSkuPoMapper.selectByPrimaryKey(productId);
			FlashSaleItem item = new FlashSaleItem(itemPo, skuPo);
			redisTemplate.opsForSet().add(key, item);
		}
	}
}

package com.jason.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.entity.Stock;
import com.jason.repository.StockDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhangjianwei
 * @version 1.0
 * @date 2023/8/28 4:05 PM
 */
@Service
public class StockService {

    @Resource
    private StockDAO stockDAO;

    /**
     * 减库存
     *
     * @param commodityCode
     * @param count
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }

        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockDAO.selectOne(wrapper);
        stock.setCount(stock.getCount() - count);

        stockDAO.updateById(stock);
    }
}

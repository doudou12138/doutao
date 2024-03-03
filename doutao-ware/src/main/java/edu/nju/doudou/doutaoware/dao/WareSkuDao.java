package edu.nju.doudou.doutaoware.dao;

import edu.nju.doudou.doutaoware.dto.SkuAvailableStock;
import edu.nju.doudou.doutaoware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品库存
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:11:42
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    /**
     * 已存在该sku库存时,增加库存
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 获取多个sku对应的库存
     * @param skuIds
     * @return
     */
    List<SkuAvailableStock> getSkuStocks(List<Long> skuIds);
}

package edu.nju.doudou.doutaoware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.to.OrderTo;
import edu.nju.doudou.common.to.mq.StockLockedTo;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoware.entity.WareSkuEntity;
import edu.nju.doudou.common.to.SkuHasStockVo;
import edu.nju.doudou.doutaoware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:11:42
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加库存
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    /**
     * 锁定库存
     * @param vo
     * @return  是否锁定成功
     */
    boolean orderLockStock(WareSkuLockVo vo);

    /**
     * 解锁库存
     * @param to
     */
    void unlockStock(StockLockedTo to);

    /**
     * 解锁订单
     * @param orderTo
     */
    void unlockStock(OrderTo orderTo);
}


package edu.nju.doudou.doutaoorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoorder.entity.OrderEntity;
import edu.nju.doudou.doutaoorder.vo.OrderConfirmVo;
import edu.nju.doudou.doutaoorder.vo.OrderSubmitVo;
import edu.nju.doudou.doutaoorder.vo.SubmitOrderResponseVo;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 20:48:59
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    /**
     * 提交订单
     * @param vo
     * @return
     */
    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    /**
     * 根据订单号查询订单状态
     * @param orderSn
     * @return
     */
    OrderEntity getOrderByOrderSn(String orderSn);

    /**
     * 尝试关单
     * @param orderEntity
     */
    void closeOrder(OrderEntity orderEntity);
}


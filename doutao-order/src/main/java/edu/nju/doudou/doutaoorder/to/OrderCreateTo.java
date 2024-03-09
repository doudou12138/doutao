package edu.nju.doudou.doutaoorder.to;


import edu.nju.doudou.doutaoorder.entity.OrderEntity;
import edu.nju.doudou.doutaoorder.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateTo {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;


}

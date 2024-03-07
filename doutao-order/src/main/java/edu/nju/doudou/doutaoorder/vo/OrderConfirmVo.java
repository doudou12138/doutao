package edu.nju.doudou.doutaoorder.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class OrderConfirmVo {
    @Getter
    @Setter
    /** 会员收获地址列表 **/
            List<MemberAddressVo> memberAddressVos;

    @Getter @Setter
    /** 所有选中的购物项 **/
            List<OrderItemVo> items;

    /** 发票记录 **/
    @Getter @Setter
    /** 优惠券（会员积分） **/
    private Integer integration;

    /** 防止重复提交的令牌 **/
    @Getter @Setter
    private String orderToken;

    @Getter @Setter
    Map<Long,Boolean> stocks;
}

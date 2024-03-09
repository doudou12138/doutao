package edu.nju.doudou.doutaoorder.vo;

import edu.nju.doudou.doutaoorder.entity.OrderEntity;
import lombok.Data;

@Data
public class SubmitOrderResponseVo {
    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;

}

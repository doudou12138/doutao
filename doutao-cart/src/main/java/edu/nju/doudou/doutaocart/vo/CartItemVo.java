package edu.nju.doudou.doutaocart.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartItemVo {
    private Long skuId;

    private Boolean check = true;

    private String title;

    private String image;

    /**
     * 商品套餐属性
     */
    private List<String> skuAttrValues;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;
}

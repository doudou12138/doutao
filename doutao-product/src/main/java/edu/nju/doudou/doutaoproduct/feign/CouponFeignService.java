package edu.nju.doudou.doutaoproduct.feign;

import edu.nju.doudou.common.to.SkuReductionTo;
import edu.nju.doudou.common.to.SpuBoundTo;
import edu.nju.doudou.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 调用优惠券服务client
 */
@FeignClient(value = "doutao-coupon")
public interface CouponFeignService {

    /**
     * 保存spu积分信息
     * @param spuBoundTo
     * @return
     */
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    /**
     *
     * @param skuReductionTo
     * @return
     */
    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);

}

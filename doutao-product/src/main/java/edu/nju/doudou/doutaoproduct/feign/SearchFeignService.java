package edu.nju.doudou.doutaoproduct.feign;

import edu.nju.doudou.common.to.es.SkuEsModel;
import edu.nju.doudou.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "doutao-search")
public interface SearchFeignService {

    /**
     * 将商品数据发给es进行保存
     * @param skuEsModels
     * @return
     */
    @PostMapping(value = "/search/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);

}

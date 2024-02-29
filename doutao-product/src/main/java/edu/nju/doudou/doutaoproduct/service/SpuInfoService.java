package edu.nju.doudou.doutaoproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoproduct.entity.SpuInfoEntity;
import edu.nju.doudou.doutaoproduct.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:13:57
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存商品信息
     * @param spuSaveVo
     */
    void saveSpuVo(SpuSaveVo spuSaveVo);
}


package edu.nju.doudou.doutaoware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoware.entity.WareInfoEntity;
import edu.nju.doudou.doutaoware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:11:42
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    /**
     * 根据条件分页查询库存信息
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据地址id获取运费
     * @param addrId
     * @return
     */
    FareVo getFare(Long addrId);
}


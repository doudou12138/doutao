package edu.nju.doudou.doutaoware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:11:42
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    /**
     * 根据条件查询采购项
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据采购单ids查询与之相关的采购项
     * @param purchaseIds
     * @return
     */
    List<PurchaseDetailEntity> listDetailByPurchaseIds(List<Long> purchaseIds);
}


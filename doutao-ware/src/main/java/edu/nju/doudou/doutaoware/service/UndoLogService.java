package edu.nju.doudou.doutaoware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:11:42
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


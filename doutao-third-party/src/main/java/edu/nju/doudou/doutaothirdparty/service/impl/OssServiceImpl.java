package edu.nju.doudou.doutaothirdparty.service.impl;

import edu.nju.doudou.doutaothirdparty.service.intf.OssService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OssServiceImpl implements OssService {

    //todo: 阿里云OSS相关接口
    @Override
    public Map<String,Object> policy(){
        Map<String, Object> respMap = new HashMap<>();
        return respMap;
    }

}

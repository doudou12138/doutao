package edu.nju.doudou.doutaothirdparty.controller;

import edu.nju.doudou.common.utils.R;
import edu.nju.doudou.doutaothirdparty.service.intf.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
public class OssController {

    @Autowired
    private OssService ossService;

    public R policy() {

        return R.ok().put(ossService.policy());
    }

}

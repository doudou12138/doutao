package edu.nju.doudou.doutaomember.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.nju.doudou.doutaomember.entity.MemberReceiveAddressEntity;
import edu.nju.doudou.doutaomember.service.MemberReceiveAddressService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.common.utils.R;



/**
 * 会员收货地址
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:03:01
 */
@RestController
@RequestMapping("doutaomember/memberreceiveaddress")
public class MemberReceiveAddressController {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("/{memberId}/address")
    public List<MemberReceiveAddressEntity> getAddress(@PathVariable("memberId") Long memberId) {
        List<MemberReceiveAddressEntity> addressList = memberReceiveAddressService.getAddress(memberId);
        return addressList;
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberReceiveAddressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(id);

        return R.ok().put("memberReceiveAddress", memberReceiveAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress){
		memberReceiveAddressService.save(memberReceiveAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberReceiveAddressEntity memberReceiveAddress){
		memberReceiveAddressService.updateById(memberReceiveAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberReceiveAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

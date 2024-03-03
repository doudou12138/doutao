package edu.nju.doudou.doutaosearch.vo;

import lombok.Data;

import java.util.List;

/**
 * 封装所有可能传过来的查询条件
 */
@Data
public class SearchParam {

    /**
     * 页面传递过来的全文匹配关键字
     */
    private String keyword;

    /**
     * 品牌id,可以多选
     */
    private List<Long> brandId;

    /**
     * 三级分类id
     */
    private Long catalog3Id;

    /**
     * 排序条件：sort=price/salecount/hotscore+_+desc/asc
     */
    private String sort;

    /**
     * 是否显示有货
     */
    private Integer hasStock;

    /**
     * 价格区间查询
     * 1_500/_500/500_
     */
    private String skuPrice;

    /**
     * 按照属性进行筛选
     *
     * 1_其他:安卓:ios
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 原生的所有查询条件
     */
    private String _queryString;


}

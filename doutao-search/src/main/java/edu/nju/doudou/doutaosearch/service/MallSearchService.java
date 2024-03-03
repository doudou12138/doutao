package edu.nju.doudou.doutaosearch.service;

import edu.nju.doudou.doutaosearch.vo.SearchParam;
import edu.nju.doudou.doutaosearch.vo.SearchResult;


public interface MallSearchService {
    /**
     * 搜索
     * @param param
     * @return
     */
    SearchResult search(SearchParam param);
}

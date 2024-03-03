package edu.nju.doudou.doutaosearch.service;

import edu.nju.doudou.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;

}

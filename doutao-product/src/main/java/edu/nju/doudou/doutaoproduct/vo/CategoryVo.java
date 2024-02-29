package edu.nju.doudou.doutaoproduct.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.nju.doudou.doutaoproduct.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends CategoryEntity {

    /**
     * 非空的时候转json才带上这个字段
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryVo> children;

}

package com.github.chenhao96.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<L> extends BaseResult<List<L>> {

    @ApiModelProperty(name = "count", value = "分页总数", required = true, example = "1")
    private int count;

    public PageResult(int value, String msg) {
        super(value, msg);
    }
}

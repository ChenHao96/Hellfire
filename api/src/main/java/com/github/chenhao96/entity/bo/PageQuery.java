package com.github.chenhao96.entity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class PageQuery {

    @ApiModelProperty(value = "分页每页条数", required = true,example = "10")
    private Integer limit;

    @ApiModelProperty(value = "当前页第一行行号", required = true,example = "1")
    private Integer page;

    @ApiModelProperty(value = "升序字段集")
    private Set<String> ascCol;

    @ApiModelProperty(value = "降序字段集")
    private Set<String> descCol;
}

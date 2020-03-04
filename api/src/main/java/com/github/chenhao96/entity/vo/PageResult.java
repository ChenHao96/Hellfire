package com.github.chenhao96.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<D> {

    @ApiModelProperty(value = "总数", required = true, example = "1")
    private long total;

    @ApiModelProperty(value = "数据")
    private List<D> records;

    @ApiModelProperty(value = "上一页", required = true)
    private boolean next;

    @ApiModelProperty(value = "下一页", required = true)
    private boolean previous;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageResult{");
        sb.append("count=").append(total);
        sb.append(", records=").append(records);
        sb.append(", next=").append(next);
        sb.append(", previous=").append(previous);
        sb.append('}');
        return sb.toString();
    }
}

package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageQuery {

    private Integer limit;

    private Integer page;
}

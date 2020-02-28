package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class PageQuery {

    private Integer limit;

    private Integer page;

    private Set<String> ascCol;

    private Set<String> descCol;
}

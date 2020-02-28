package com.github.chenhao96.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<L> extends BaseResult<List<L>> {

    private int count;
}

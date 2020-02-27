package com.github.chenhao96.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<D> {

    private int code;

    private String msg;

    private int count;

    private List<D> data;
}

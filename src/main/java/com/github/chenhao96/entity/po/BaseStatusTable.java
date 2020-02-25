package com.github.chenhao96.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseStatusTable extends BaseTable {

    private Integer status;
}

package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageCondition {

    private String column;

    private Object value;

    private boolean eq;

}

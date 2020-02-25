package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class BaseLogicTable extends BaseTable {

    @TableLogic(value = "0", delval = "1")
    private Boolean logic = false;
}

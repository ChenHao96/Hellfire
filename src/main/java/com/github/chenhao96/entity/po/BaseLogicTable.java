package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;

public class BaseLogicTable extends BaseTable {

    @TableLogic
    private Boolean logic;

    public Boolean getLogic() {
        return logic;
    }

    public void setLogic(Boolean logic) {
        this.logic = logic;
    }
}

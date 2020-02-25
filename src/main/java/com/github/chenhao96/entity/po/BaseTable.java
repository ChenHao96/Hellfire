package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public abstract class BaseTable implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
}

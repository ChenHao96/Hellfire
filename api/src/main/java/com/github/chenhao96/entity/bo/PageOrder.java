package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageOrder {

    private String column;

    private boolean asc;

    private int index;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageOrder{");
        sb.append("column='").append(column).append('\'');
        sb.append(", asc=").append(asc);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}

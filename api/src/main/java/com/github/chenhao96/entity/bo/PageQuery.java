package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class PageQuery {

    private Integer limit;

    private Integer page;

    private Set<PageOrder> orders;

    private Set<PageCondition> conditions;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageQuery{");
        sb.append("limit=").append(limit);
        sb.append(", page=").append(page);
        sb.append(", orders=").append(orders);
        sb.append(", conditions=").append(conditions);
        sb.append('}');
        return sb.toString();
    }
}

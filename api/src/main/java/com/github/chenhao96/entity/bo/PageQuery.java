package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class PageQuery {

    private Integer limit;

    private Integer page;

    private Set<String> ascCol;

    private Set<String> descCol;

    private Map<String, Object> condition;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageQuery{");
        sb.append("limit=").append(limit);
        sb.append(", page=").append(page);
        sb.append(", ascCol=").append(ascCol);
        sb.append(", descCol=").append(descCol);
        sb.append(", condition=").append(condition);
        sb.append('}');
        return sb.toString();
    }
}

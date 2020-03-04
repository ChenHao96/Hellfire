package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.vo.PageResult;

public interface BaseAdaptor<D> {

    boolean saveNewRecord(D record);

    boolean updateRecordInfo(D record);

    D queryRecordById(Integer id);

    boolean deleteRecord(Integer id);

    PageResult<D> queryPage(PageQuery query);
}

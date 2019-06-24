package com.zyth.web.mapper;

import com.zyth.web.bean.Sos;

public interface SosMapper {
    int insert(Sos record);

    int insertSelective(Sos record);
}
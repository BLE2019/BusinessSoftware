package com.zyth.web.mapper;

import com.zyth.web.bean.LocationBmap;

public interface LocationBmapMapper {
    int insert(LocationBmap record);

    int insertSelective(LocationBmap record);
}
package com.zyth.web.mapper;

import java.util.List;

import com.zyth.web.bean.LocationFmap;

public interface LocationFmapMapper {
    int insert(LocationFmap record);

    int insertSelective(LocationFmap record);

	List<LocationFmap> getLatestData();
}
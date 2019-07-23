package com.zyth.web.mapper;

import java.util.List;

import com.zyth.web.bean.LocationFmap;
import com.zyth.web.bean.vo.LocationFVO;

public interface LocationFmapMapper {
    int insert(LocationFmap record);

    int insertSelective(LocationFmap record);
    int updateLatest(LocationFmap record);

	List<LocationFVO> getLatestData();
	List<LocationFVO> getHistory(LocationFVO locationFVO);

}
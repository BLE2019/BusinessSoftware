package com.zyth.web.mapper;

import java.util.Date;
import java.util.List;

import com.zyth.web.bean.Beacon;

public interface BeaconMapper {
    int insert(Beacon record);

    int insertSelective(Beacon record);

    List<Beacon> getLatestData(Date latestTime);
}
package com.zyth.web.mapper;

import java.util.List;

import com.zyth.web.bean.BeaconLoc;

public interface BeaconLocMapper {
    int insert(BeaconLoc record);

    int insertSelective(BeaconLoc record);

    List<BeaconLoc> getAllData();
}
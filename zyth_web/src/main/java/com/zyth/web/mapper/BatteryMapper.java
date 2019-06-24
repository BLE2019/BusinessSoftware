package com.zyth.web.mapper;

import com.zyth.web.bean.Battery;

public interface BatteryMapper {
    int insert(Battery record);

    int insertSelective(Battery record);
}
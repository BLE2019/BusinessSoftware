package com.zyth.web.mapper;

import com.zyth.web.bean.SosDeal;

public interface SosDealMapper {
    int insert(SosDeal record);

    int insertSelective(SosDeal record);
}
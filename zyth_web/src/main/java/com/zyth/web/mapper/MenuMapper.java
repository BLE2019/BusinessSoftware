package com.zyth.web.mapper;

import java.util.List;

import com.zyth.web.bean.Menu;
import com.zyth.web.bean.vo.MenuVO;

public interface MenuMapper {
    int deleteByPrimaryKey(String menuNo);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuNo);
    List<MenuVO> selectByLevel(String menuLevel);
    List<MenuVO> selectByParent(String parentMenuNo);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}
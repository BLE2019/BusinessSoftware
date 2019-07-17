package com.zyth.web.mapper;

import java.util.List;

import com.zyth.web.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    List<User> selectList(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
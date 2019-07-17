package com.zyth.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyth.web.bean.User;
import com.zyth.web.bean.vo.MenuVO;
import com.zyth.web.mapper.UserMapper;
import com.zyth.web.service.UserServiceApi;


@Service("userService")
public class UserService implements UserServiceApi {

	@Resource
	UserMapper userMapper;

	public List<User> getList(User user) {
		List<User> userList = userMapper.selectList(user);
		return userList;
	}

	public boolean saveUser(User user) {
		//用户id存在，更新
		if(user.getUserId() != null){
			userMapper.updateByPrimaryKeySelective(user);
		}else{
			//用户id不存在，新增
			userMapper.insertSelective(user);
		}
		return true;
	}

	public User getUser(int userId) {
		User user = new User();
		user.setUserId(userId);
		List<User> userList = userMapper.selectList(user);
		return userList.get(0);
	}

}

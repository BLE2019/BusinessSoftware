package com.zyth.web.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyth.web.bean.User;
import com.zyth.web.bean.vo.MenuVO;
import com.zyth.web.common.util.ExcelInputUtil;
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

	public boolean upload(InputStream in) {
		ArrayList dataList = ExcelInputUtil.excelToList(in);

		for (Object object : dataList) {

			HashMap<String, String> tempMap = (HashMap<String, String>) object;
			User user = new User();
			user.setDeveui(tempMap.get("0"));
			user.setUserName(tempMap.get("1"));
			user.setSex(Integer.valueOf(tempMap.get("2")));
			user.setUserType(tempMap.get("3"));
			saveUser(user);
		}
		return true;
	}

}

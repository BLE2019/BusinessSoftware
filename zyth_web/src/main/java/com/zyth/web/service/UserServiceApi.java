package com.zyth.web.service;

import java.io.InputStream;
import java.util.List;

import com.zyth.web.bean.User;

public interface UserServiceApi {
	public List<User> getList(User user);

	public boolean saveUser(User user);

	public User getUser(int userId);
	public boolean upload(InputStream in);
}

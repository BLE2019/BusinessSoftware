package com.zyth.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyth.web.bean.User;
import com.zyth.web.common.basic.JsonResult;
import com.zyth.web.service.UserServiceApi;



@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserServiceApi UserService;

	@RequestMapping("getList.htm")
	@ResponseBody
	public JsonResult getMenu(User user,HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try{
			json.setData(UserService.getList(user));
			json.setOk(true);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			json.setOk(false);
			return json;
		}
	}

	@RequestMapping("saveUser.htm")
	@ResponseBody
	public JsonResult saveUser(User user,HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try{
			json.setData(UserService.saveUser(user));
			json.setOk(true);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			json.setOk(false);
			return json;
		}
	}


	@RequestMapping("getUser.htm")
	@ResponseBody
	public JsonResult getUser(int userId,HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try{
			json.setData(UserService.getUser(userId));
			json.setOk(true);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			json.setOk(false);
			return json;
		}
	}
}

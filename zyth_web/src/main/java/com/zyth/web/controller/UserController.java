package com.zyth.web.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zyth.web.common.util.DateTimeUtils;
import com.zyth.web.common.util.ExcelUtil;
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
	public JsonResult getList(User user,HttpServletRequest request,
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


	@RequestMapping("upload.htm")
	@ResponseBody
	public JsonResult upload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try{
			json.setData(UserService.upload(file.getInputStream()));
			json.setOk(true);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			json.setOk(false);
			return json;
		}
	}

	@RequestMapping("downLoad.htm")
	@ResponseBody
	public void downLoad(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			ExcelUtil<User> vExcelUtil = new ExcelUtil<User>();//导出类初始化
			List<User> list = UserService.getList(new User());
			for (int i=0;i<list.size();i++) {
				list.get(i);

			}
            OutputStream out = null;
            String []excelHeader = {"SN","用户姓名","性别（0：男，1：女）","人员类型"};
            String []fileds = {"deveui","userName","sex","userType"};
            response.setHeader("Content-disposition","attachment; filename="+ URLEncoder.encode(System.currentTimeMillis()+"user.xls", "UTF-8"));
            try{
                out = response.getOutputStream();
                String title = "用户模板";
                vExcelUtil.exportExcel(title, excelHeader, fileds, list, out, "yyyy-MM-dd");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}

	@RequestMapping("downModal.htm")
	@ResponseBody
	public void downModal(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			ExcelUtil<User> vExcelUtil = new ExcelUtil<User>();//导出类初始化
            OutputStream out = null;
            String []excelHeader = {"SN","用户姓名","性别（0：男，1：女）","人员类型"};
            String []fileds = {"deveui","userName","sex","userType"};
            response.setHeader("Content-disposition","attachment; filename=user.xls");
            try{
                out = response.getOutputStream();
                String title = "用户数据";
                vExcelUtil.exportExcel(title, excelHeader, fileds, new ArrayList<User>(), out, "yyyy-MM-dd");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
		}catch(Exception e){
			logger.error(e.getMessage(),e);
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

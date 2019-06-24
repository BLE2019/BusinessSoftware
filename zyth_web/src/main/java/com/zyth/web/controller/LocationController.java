package com.zyth.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyth.web.common.basic.JsonResult;
import com.zyth.web.service.OperatorServiceApi;



@Controller
@RequestMapping("location")
public class LocationController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

	@Resource
	private OperatorServiceApi operatorService;

	@RequestMapping("getInitData.htm")
	@ResponseBody
	public JsonResult getMenu( HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try{
			json.setData(operatorService.getMenuList());
			json.setOk(true);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			json.setOk(false);
			return json;
		}
	}

}

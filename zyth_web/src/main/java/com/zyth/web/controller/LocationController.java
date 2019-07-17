package com.zyth.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zyth.web.bean.Area;
import com.zyth.web.bean.LocationFmap;
import com.zyth.web.bean.vo.LocationFVO;
import com.zyth.web.common.basic.JsonResult;
import com.zyth.web.common.basic.SessionManager;
import com.zyth.web.service.OperatorServiceApi;



@Controller
@RequestMapping("location")
public class LocationController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

	@Resource
	private OperatorServiceApi operatorService;

	@RequestMapping("getData.htm")
	@ResponseBody
	public JsonResult getData( HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try{

    	    RedisTemplate<String, Object> redis = SessionManager.getRedisTemplate();
    	    String currentDataF = (String) redis.opsForValue().get("currentDataF");

    	    HashMap<String, LocationFVO>  currentDataFMap = JSON.parseObject(currentDataF, HashMap.class);


    	    String areaDataStr = (String) redis.opsForValue().get("areaDataStr");

    	    List<Area> areaData = JSON.parseObject(areaDataStr, List.class);

    	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	    resultMap.put("mapData", new ArrayList<LocationFVO>(currentDataFMap.values()));
    	    resultMap.put("areaData", areaData);
			json.setData(resultMap);
			json.setOk(true);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			json.setOk(false);
			return json;
		}
	}

}

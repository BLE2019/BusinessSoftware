package com.zyth.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyth.web.bean.Area;
import com.zyth.web.bean.Beacon;
import com.zyth.web.bean.BeaconLoc;
import com.zyth.web.bean.LocationFmap;
import com.zyth.web.bean.vo.LocationFVO;
import com.zyth.web.common.basic.SessionManager;
import com.zyth.web.mapper.AreaMapper;
import com.zyth.web.mapper.BeaconLocMapper;
import com.zyth.web.mapper.BeaconMapper;
import com.zyth.web.mapper.LocationFmapMapper;
import com.zyth.web.service.LocationServiceApi;


@Service("locationService")
public class LocationService implements LocationServiceApi {

	@Resource
	LocationFmapMapper locationFmapMapper;

	@Resource
	BeaconMapper beaconMapper;

	@Resource
	BeaconLocMapper beaconLocMapper;

	@Resource
	AreaMapper areaMapper;

	public void sysInit() {
		Runnable runnable = new Runnable() {
	      public void run() {
	    	  try{
		    	  RedisTemplate<String, Object> redis = SessionManager.getRedisTemplate();
		    	  Date latestTime =
		    			  (Date) redis.opsForValue().get("latestTime");

		    	  String currentDataFStr = (String)redis.opsForValue().get("currentDataF");
		    	  HashMap<String, Object> currentDataF = null;
		    	  //如果当前数据为空
		    	  if(currentDataFStr==null){
		    		  currentDataF = new HashMap<String, Object>();
		    		  //查询数据库最新的位置数据
		    		  List<LocationFVO> currentList = locationFmapMapper.getLatestData();
		    		  for (LocationFVO locationFmap : currentList) {
		    			  currentDataF.put(locationFmap.getDeveui(), locationFmap);
		    			  //获得数据库中最新的时间
		    			  if(latestTime==null || latestTime.compareTo(locationFmap.getRcvtime())<0){
		    				  latestTime = locationFmap.getRcvtime();
		    			  }
		    		  }
		    	  }else{
		    		  currentDataF = JSON.parseObject(currentDataFStr, HashMap.class);
		    	  }


		    	  //TODO 待优化，不用每次都取，获取蓝牙信标数据
		    	  List<BeaconLoc> beaconLocList = beaconLocMapper.getAllData();
		    	  HashMap<String, BeaconLoc> beaconLocMap = new HashMap<String, BeaconLoc>();
		    	  for (BeaconLoc beaconLoc : beaconLocList) {
		    		  beaconLocMap.put(beaconLoc.getBeaconId(), beaconLoc);
		    	  }

		    	  //获取未处理的数据
		    	  List<Beacon> beanconList = beaconMapper.getLatestData(latestTime);
		    	  for (Beacon beacon : beanconList) {
		    		  LocationFmap locationFmap = new LocationFmap();
		    		  locationFmap.setDeveui(beacon.getDeveui());
		    		  locationFmap.setIsLatest(1);
		    		  locationFmap.setRcvtime(beacon.getRcvtime());
		    		  locationFmap.setUserId(beacon.getUserId());

		    		  //切取beacon数据
		    		  String[] beaconMsg = beacon.getBeaconmsg().split("-");
		    		  //场强线性累加值
		    		  double rssiCalSum = 0;
		    		  //第一次循环获取累计值
		    		  for (String beaconInfo : beaconMsg) {
		    			  //获取后两位的rssi值
		    			  String rssiStr = beaconInfo.substring(8);
		    			  //16进制字符串转为int，并减去255，得到实际场强值
		    			  int rssi = Integer.parseInt(rssiStr, 16)-255;
		    			  //转化为线性值
		    			  double rssiCal=Math.pow(10, rssi/10);
		    			  //计算累加值
		    			  rssiCalSum +=rssiCal;
		    		  }

		    		  //坐标加权合计值
		    		  float mapXSum = 0;
		    		  float mapYSum = 0;
		    		  float mapZSum = 0;

		    		  //第二次循环计算
		    		  for (String beaconInfo : beaconMsg) {
		    			  //获取后两位的rssi值
		    			  String rssiStr = beaconInfo.substring(8);
		    			  //16进制字符串转为int，并减去255，得到实际场强值
		    			  int rssi = Integer.parseInt(rssiStr, 16)-255;
		    			  //转化为线性值
		    			  double rssiCal=Math.pow(10, rssi/10);

		    			  //权重计算，归一化
		    			  double rate = rssiCal/rssiCalSum;

		    			  //前8位是major+minor作为主键
		    			  String beaconId = beaconInfo.substring(0,8);
		    			  BeaconLoc beaconLoc = beaconLocMap.get(beaconId);

		    			  //获取对应坐标并乘权重，加到累加值上
		    			  mapXSum += beaconLoc.getMapX()*rate;
		    			  mapYSum += beaconLoc.getMapY()*rate;
		    			  mapZSum += beaconLoc.getMapZ()*rate;
		    		  }

		    		  //四舍五入并将坐标值存入对象中
		    		  locationFmap.setMapX(Math.round(mapXSum));
		    		  locationFmap.setMapY(Math.round(mapYSum));
		    		  locationFmap.setMapZ(Math.round(mapZSum));


			    	  String areaDataStr = (String)redis.opsForValue().get("areaDataStr");
			    	  List<Area> areaData = null;
			    	  //如果当前数据为空
			    	  if(areaDataStr==null){
			    		  //获取区域数据
			    		  areaData = areaMapper.selectAll();
			    	  }else{
			    		  areaData = JSON.parseArray(areaDataStr, Area.class);
			    	  }
			    	  Area currentArea = null;
		    		  for (Area area : areaData) {
		    			  if(locationFmap.getMapZ() == area.getMapZ()
		    					  && locationFmap.getMapX()>=area.getMapX0() && locationFmap.getMapX()<=area.getMapX1()
		    					  && locationFmap.getMapY()>=area.getMapY0() && locationFmap.getMapX()<=area.getMapY1()){
		    				  currentArea = area;
		    				  locationFmap.setAreaId(area.getAreaId());
		    				  break;
		    			  }
		    		  }


		    		//如果对应的人员本身存在，对比位置差异
					JSONObject oldLocationJson =  (JSONObject) currentDataF.get(locationFmap.getDeveui());
					if(oldLocationJson!=null){
						LocationFVO oldLocation = JSON.toJavaObject(oldLocationJson,LocationFVO.class);
						//mapz不一致，楼层发生了变化，或者X、Y值变化大于一定范围，或者区域id不一致
						if(oldLocation.getMapZ() != locationFmap.getMapZ() ||
								  Math.abs(oldLocation.getMapX() - locationFmap.getMapX())>5 ||
								  Math.abs(oldLocation.getMapY() - locationFmap.getMapY())>5 ||
								  oldLocation.getAreaId() != locationFmap.getAreaId()){
							//将计算好的数据更新到redis中
							currentDataF.put(locationFmap.getDeveui(), locationFmap);
							redis.opsForValue().set("currentDataF",JSON.toJSONString(currentDataF));

				    		//原来所在的区域人数减1
				    		for (Area area : areaData) {
				    			if(oldLocation.getAreaId() == area.getAreaId()){
				    				area.setUserCount(area.getUserCount()-1);
				    				break;
				    			}
				    		}

				    		//将区域放到对应统计位置
			    			currentArea.setUserCount(currentArea.getUserCount()+1);
						}
						//将现有的数据更新为不是最新的
						locationFmapMapper.updateLatest(locationFmap);
		    		}else{
			    		//将区域放到对应统计位置
		    			currentArea.setUserCount(currentArea.getUserCount()+1);

			    		//将计算好的数据更新到redis中
		    			currentDataF.put(locationFmap.getDeveui(), locationFmap);
		    			redis.opsForValue().set("currentDataF",JSON.toJSONString(currentDataF));
		    		}
	    			redis.opsForValue().set("areaDataStr",JSON.toJSONString(areaData));

	    			//将计算好的数据保存到数据库中
					locationFmapMapper.insertSelective(locationFmap);
				}
	    	  }catch(Exception e){
	    		  e.printStackTrace();
	    	  }
	      }
	    };
	    ScheduledExecutorService service = Executors
	                    .newSingleThreadScheduledExecutor();
	    //每5分钟执行一次
	    service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.MINUTES);
	}


}

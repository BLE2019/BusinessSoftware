package com.zyth.web.bean;

public class Area {
    private Integer areaId;

    private Integer mapX0;

    private Integer mapY0;

    private Integer mapX1;

    private Integer mapY1;

    private Integer mapZ;

    private String areaName;

    private int userCount;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

	public Integer getMapX0() {
		return mapX0;
	}

	public void setMapX0(Integer mapX0) {
		this.mapX0 = mapX0;
	}

	public Integer getMapY0() {
		return mapY0;
	}

	public void setMapY0(Integer mapY0) {
		this.mapY0 = mapY0;
	}

	public Integer getMapX1() {
		return mapX1;
	}

	public void setMapX1(Integer mapX1) {
		this.mapX1 = mapX1;
	}

	public Integer getMapY1() {
		return mapY1;
	}

	public void setMapY1(Integer mapY1) {
		this.mapY1 = mapY1;
	}

	public Integer getMapZ() {
		return mapZ;
	}

	public void setMapZ(Integer mapZ) {
		this.mapZ = mapZ;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}
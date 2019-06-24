package com.zyth.web.bean;

public class Area {
    private Integer areaId;

    private String mapX0;

    private String mapY0;

    private String mapX1;

    private String mapY1;

    private String mapZ;

    private String areaName;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getMapX0() {
        return mapX0;
    }

    public void setMapX0(String mapX0) {
        this.mapX0 = mapX0 == null ? null : mapX0.trim();
    }

    public String getMapY0() {
        return mapY0;
    }

    public void setMapY0(String mapY0) {
        this.mapY0 = mapY0 == null ? null : mapY0.trim();
    }

    public String getMapX1() {
        return mapX1;
    }

    public void setMapX1(String mapX1) {
        this.mapX1 = mapX1 == null ? null : mapX1.trim();
    }

    public String getMapY1() {
        return mapY1;
    }

    public void setMapY1(String mapY1) {
        this.mapY1 = mapY1 == null ? null : mapY1.trim();
    }

    public String getMapZ() {
        return mapZ;
    }

    public void setMapZ(String mapZ) {
        this.mapZ = mapZ == null ? null : mapZ.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }
}
package com.zyth.web.bean;

public class BeaconLoc {
    private String beaconId;

    private Integer mapX;

    private Integer mapY;

    private Integer mapZ;

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId == null ? null : beaconId.trim();
    }

    public Integer getMapX() {
        return mapX;
    }

    public void setMapX(Integer mapX) {
        this.mapX = mapX;
    }

    public Integer getMapY() {
        return mapY;
    }

    public void setMapY(Integer mapY) {
        this.mapY = mapY;
    }

    public Integer getMapZ() {
        return mapZ;
    }

    public void setMapZ(Integer mapZ) {
        this.mapZ = mapZ;
    }
}
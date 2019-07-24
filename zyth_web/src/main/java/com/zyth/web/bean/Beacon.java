package com.zyth.web.bean;

import java.util.Date;

public class Beacon {
    private String deveui;

    private String appeui;

    private String msgtype;

    private Integer beaconnum;

    private String beaconmsg;

    private Date rcvtime;

    private String rsv;

    private Integer userId;

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui == null ? null : deveui.trim();
    }

    public String getAppeui() {
        return appeui;
    }

    public void setAppeui(String appeui) {
        this.appeui = appeui == null ? null : appeui.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public Integer getBeaconnum() {
        return beaconnum;
    }

    public void setBeaconnum(Integer beaconnum) {
        this.beaconnum = beaconnum;
    }

    public String getBeaconmsg() {
        return beaconmsg;
    }

    public void setBeaconmsg(String beaconmsg) {
        this.beaconmsg = beaconmsg == null ? null : beaconmsg.trim();
    }

    public Date getRcvtime() {
        return rcvtime;
    }

    public void setRcvtime(Date rcvtime) {
        this.rcvtime = rcvtime;
    }

    public String getRsv() {
        return rsv;
    }

    public void setRsv(String rsv) {
        this.rsv = rsv == null ? null : rsv.trim();
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
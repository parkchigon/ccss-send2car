package com.lgu.ccss.send2car.model;

public class Send2CarParam {
	private String svrId;
	private String notiTimeSec;
	
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public String getNotiTimeSec() {
		return notiTimeSec;
	}
	public void setNotiTimeSec(String notiTimeSec) {
		this.notiTimeSec = notiTimeSec;
	}
	
	@Override
	public String toString() {
		return "Send2CarParam [svrId=" + svrId + ", notiTimeSec=" + notiTimeSec + "]";
	}
}

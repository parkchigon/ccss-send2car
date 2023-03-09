package com.lgu.ccss.common.model;

import java.util.Date;

public class AIAuthInfoVO {
	private int aiAuthSeq;
	private String membId;
	private String deviceToken;
	private String platformToken;
	private String useYn;
	private String aiAuthExpDt;
	private Date regDt;
	private String regId;
	private Date updDt;
	private String updId;
	
	public int getAiAuthSeq() {
		return aiAuthSeq;
	}
	public void setAiAuthSeq(int aiAuthSeq) {
		this.aiAuthSeq = aiAuthSeq;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getPlatformToken() {
		return platformToken;
	}
	public void setPlatformToken(String platformToken) {
		this.platformToken = platformToken;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getAiAuthExpDt() {
		return aiAuthExpDt;
	}
	public void setAiAuthExpDt(String aiAuthExpDt) {
		this.aiAuthExpDt = aiAuthExpDt;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("====================== AIAuthInfoVO ======================").append("\r\n");
	    sb.append("aiAuthSeq      :").append(aiAuthSeq).append("\r\n");
	    sb.append("membId         :").append(membId).append("\r\n");
	    sb.append("deviceToken    :").append(deviceToken).append("\r\n");
	    sb.append("platformToken  :").append(platformToken).append("\r\n");
	    sb.append("useYn          :").append(useYn).append("\r\n");
	    sb.append("aiAuthExpDt    :").append(aiAuthExpDt).append("\r\n");
	    sb.append("regDt          :").append(regDt).append("\r\n");
	    sb.append("regId          :").append(regId).append("\r\n");
	    sb.append("updDt          :").append(updDt).append("\r\n");
	    sb.append("updId          :").append(updId).append("\r\n");
		return sb.toString();
				
	}
}

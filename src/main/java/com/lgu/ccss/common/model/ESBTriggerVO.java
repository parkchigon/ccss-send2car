package com.lgu.ccss.common.model;

import java.util.Date;

public class ESBTriggerVO {
	
	/*
	 * TRIG_TYPE	VARCHAR2(10 BYTE)
TRIG_DATE	DATE
TRIG_STATUS	VARCHAR2(2 BYTE)
MEMB_ID	VARCHAR2(20 BYTE)
SVR_ID	VARCHAR2(2 BYTE)
DEVICE_CTN	VARCHAR2(20 BYTE)
REG_ID	VARCHAR2(20 BYTE)
REG_DATE	DATE
UPD_ID	VARCHAR2(20 BYTE)
UPD_DT	DATE
TRIG_ID	VARCHAR2(18 BYTE)
	 */
	public static final String TRIGGER_STATUS_REQUEST 	= "00";
	public static final String TRIGGER_STATUS_SUCCESS 	= "01";
	public static final String TRIGGER_STATUS_FAIL 		= "09";
	
	private String trigID = null;
	private String trigType = null;
	private Date trigDt = null;
	private String trigStatus = null;
	private String membID = null;
	private String svrID = null;
	private String deviceCTN = null;
	private String regID = null;
	private Date regDt = null;
	private String updID = null;
	private Date updDt = null;
	private String resultCode = null;
	private int rownum;
	
	public String getTrigID() {
		return trigID;
	}
	public void setTrigID(String trigID) {
		this.trigID = trigID;
	}
	public String getTrigType() {
		return trigType;
	}
	public void setTrigType(String trigType) {
		this.trigType = trigType;
	}
	public Date getTrigDt() {
		return trigDt;
	}
	public void setTrigDt(Date trigDt) {
		this.trigDt = trigDt;
	}
	public String getTrigStatus() {
		return trigStatus;
	}
	public void setTrigStatus(String trigStatus) {
		this.trigStatus = trigStatus;
	}
	public String getMembID() {
		return membID;
	}
	public void setMembID(String membID) {
		this.membID = membID;
	}
	public String getSvrID() {
		return svrID;
	}
	public void setSvrID(String svrID) {
		this.svrID = svrID;
	}
	public String getDeviceCTN() {
		return deviceCTN;
	}
	public void setDeviceCTN(String deviceCTN) {
		this.deviceCTN = deviceCTN;
	}
	public String getRegID() {
		return regID;
	}
	public void setRegID(String regID) {
		this.regID = regID;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getUpdID() {
		return updID;
	}
	public void setUpdID(String updID) {
		this.updID = updID;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultcode) {
		this.resultCode = resultcode;
	}
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("====================== ESBTriggerVO ======================").append("\r\n");
		sb.append("trigID	      :").append(trigID).append("\r\n");
	    sb.append("trigType       :").append(trigType).append("\r\n");
	    sb.append("trigDt         :").append(trigDt).append("\r\n");
	    sb.append("trigStatus     :").append(trigStatus).append("\r\n");
	    sb.append("resultCode     :").append(resultCode).append("\r\n");
	    sb.append("membID         :").append(membID).append("\r\n");
	    sb.append("svrID	      :").append(svrID).append("\r\n");
	    sb.append("deviceCTN      :").append(deviceCTN).append("\r\n");
	    sb.append("regID          :").append(regID).append("\r\n");
	    sb.append("regDt		  :").append(regDt).append("\r\n");
	    sb.append("updID		  :").append(updID).append("\r\n");
	    sb.append("updDt	      :").append(updDt).append("\r\n");
	    
		return sb.toString();
				
	}
	
	
}

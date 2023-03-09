package com.lgu.ccss.common.model;

import java.util.Date;

public class AppPushQueueVO {
	
	public static final String SEND_TYPE_EMERGENCY = "0"; 
	public static final String SEND_TYPE_NORMAL = "1"; 
	
	public static final String CODE_TARGET_PUSH = "S001";
	public static final String CODE_SCHEDULE_PUSH = "S002";
	
	public static final String DEVICE_TYPE_ANDROID = "A";
	public static final String DEVICE_TYPE_IPHONE = "I";
	
	public static final String REQ_PART_SP = "SP";

	private String msgId;
	private String msgStatus;
	private String code;
	private String msgTitle;
	private String msgCont;
	private String msgType;
	private String recvPhoneNo;
	private String sendType;
	private String svrId;
	private String orgNo;
	private String callbackNo;
	private String sendDt;
	private String deviceType;
	private String reqPart;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	private String carOem;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgCont() {
		return msgCont;
	}
	public void setMsgCont(String msgCont) {
		this.msgCont = msgCont;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getRecvPhoneNo() {
		return recvPhoneNo;
	}
	public void setRecvPhoneNo(String recvPhoneNo) {
		this.recvPhoneNo = recvPhoneNo;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getCallbackNo() {
		return callbackNo;
	}
	public void setCallbackNo(String callbackNo) {
		this.callbackNo = callbackNo;
	}	
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getReqPart() {
		return reqPart;
	}
	public void setReqPart(String reqPart) {
		this.reqPart = reqPart;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String getCarOem() {
		return carOem;
	}
	public void setCarOem(String carOem) {
		this.carOem = carOem;
	}
	
	@Override
	public String toString() {
		return "AppPushQueueVO [msgId=" + msgId + ", msgStatus=" + msgStatus + ", code=" + code + ", msgTitle="
				+ msgTitle + ", msgCont=" + msgCont + ", msgType=" + msgType + ", recvPhoneNo=" + recvPhoneNo
				+ ", sendType=" + sendType + ", svrId=" + svrId + ", orgNo=" + orgNo + ", callbackNo=" + callbackNo
				+ ", sendDt=" + sendDt + ", deviceType=" + deviceType + ", reqPart=" + reqPart + ", regId=" + regId
				+ ", regDt=" + regDt + ", updId=" + updId + ", updDt=" + updDt + "]";
	}
}

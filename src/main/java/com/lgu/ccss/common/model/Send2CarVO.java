package com.lgu.ccss.common.model;


public class Send2CarVO {
	
	public final static String USE_Y = "Y";
	
	public final static String SEND_STATUS_READY = "READY";
	public final static String SEND_STATUS_SCHEDULE = "SCHEDULE";
	public final static String SEND_STATUS_SEND = "SEND";
	public final static String SEND_STATUS_SENDING = "SENDING";
	public final static String SEND_STATUS_RECV = "RECV";
	public final static String SEND_STATUS_FINAL = "FINAL";
	public final static String SEND_STATUS_SLEEP = "SLEEP";
	public final static String SEND_STATUS_DELETE = "DELETE";
	
	public static final String CODE_TARGET_PUSH_CODE = 		"S001";
	public static final String CODE_RESERVE_TARGET_PUSH = 	"S101";
	public static final String CODE_GOOGLE_SCHEDULE_PUSH = "S002";
	public static final String CODE_SCHEDULE_NOTI_PUSH = 	"S003";
	public static final String CODE_KAKAO_SHARE_LOC_PUSH =	"S004";
	public static final String CODE_OTHER_SHARE_LOC_PUSH =	"S006";
	public static final String CODE_MUSIC_LOGIN_PUSH = 		"I001";
	public static final String CODE_MUSIC_LOGOUT_PUSH = 	"I011";
	public static final String CODE_PODCAST_LOGIN_PUSH = 	"I002";
	public static final String CODE_PODCAST_LOGOUT_PUSH = 	"I012";
	public static final String CODE_IOT_LOGIN_PUSH = 		"I003";
	public static final String CODE_IOT_LOGOUT_PUSH = 		"I013";
	public static final String CODE_GOOGLE_LOGIN_PUSH = 	"I004";
	public static final String CODE_GOOGLE_LOGOUT_PUSH = 	"I014";
	
	private String sendToCarId;
	private String membId;
	private String mgrappId;
	
	private String targetNm;
	private String targetAddress;
	private String targetLonx;
	private String targetLaty;
	
	private String arrivHopeTime;
	private String estTime;
	private String sendStatus;
	private String useYn;

	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	
	private String serviceType;
	private String rsvType;
	private String svrId;
	private String processDt;
	private String scheduleId;
	
	private String targetRealLonx;
	private String targetRealLaty;
	private String targetPoiId;
	private String targetRoadName;
	private String targetRoadJibun;
	private String searchOption;
	
	private String carOem;
	
	public String getSendToCarId() {
		return sendToCarId;
	}
	public void setSendToCarId(String sendToCarId) {
		this.sendToCarId = sendToCarId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getTargetNm() {
		return targetNm;
	}
	public void setTargetNm(String targetNm) {
		this.targetNm = targetNm;
	}
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
	public String getTargetLonx() {
		return targetLonx;
	}
	public void setTargetLonx(String targetLonx) {
		this.targetLonx = targetLonx;
	}
	public String getTargetLaty() {
		return targetLaty;
	}
	public void setTargetLaty(String targetLaty) {
		this.targetLaty = targetLaty;
	}
	public String getArrivHopeTime() {
		return arrivHopeTime;
	}
	public void setArrivHopeTime(String arrivHopeTime) {
		this.arrivHopeTime = arrivHopeTime;
	}
	public String getEstTime() {
		return estTime;
	}
	public void setEstTime(String estTime) {
		this.estTime = estTime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getRsvType() {
		return rsvType;
	}
	public void setRsvType(String rsvType) {
		this.rsvType = rsvType;
	}
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public String getProcessDt() {
		return processDt;
	}
	public void setProcessDt(String processDt) {
		this.processDt = processDt;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getCarOem() {
		return carOem;
	}
	public void setCarOem(String carOem) {
		this.carOem = carOem;
	}
	
	public String getTargetRealLonx() {
		return targetRealLonx;
	}
	public void setTargetRealLonx(String targetRealLonx) {
		this.targetRealLonx = targetRealLonx;
	}
	public String getTargetRealLaty() {
		return targetRealLaty;
	}
	public void setTargetRealLaty(String targetRealLaty) {
		this.targetRealLaty = targetRealLaty;
	}
	public String getTargetPoiId() {
		return targetPoiId;
	}
	public void setTargetPoiId(String targetPoiId) {
		this.targetPoiId = targetPoiId;
	}
	public String getTargetRoadName() {
		return targetRoadName;
	}
	public void setTargetRoadName(String targetRoadName) {
		this.targetRoadName = targetRoadName;
	}
	public String getTargetRoadJibun() {
		return targetRoadJibun;
	}
	public void setTargetRoadJibun(String targetRoadJibun) {
		this.targetRoadJibun = targetRoadJibun;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	@Override
	public String toString() {
		return "Send2CarVO [sendToCarId=" + sendToCarId + ", membId=" + membId + ", mgrappId=" + mgrappId
				+ ", targetNm=" + targetNm + ", targetAddress=" + targetAddress + ", targetLonx=" + targetLonx
				+ ", targetLaty=" + targetLaty + ", arrivHopeTime=" + arrivHopeTime + ", estTime=" + estTime
				+ ", sendStatus=" + sendStatus + ", useYn=" + useYn + ", regId=" + regId + ", regDt=" + regDt
				+ ", updId=" + updId + ", updDt=" + updDt + ", serviceType=" + serviceType + ", rsvType=" + rsvType
				+ ", svrId=" + svrId + ", processDt=" + processDt + ", scheduleId=" + scheduleId + ", targetRealLonx="
				+ targetRealLonx + ", targetRealLaty=" + targetRealLaty + ", targetPoiId=" + targetPoiId
				+ ", targetRoadName=" + targetRoadName + ", targetRoadJibun=" + targetRoadJibun + ", searchOption="
				+ searchOption + ", carOem=" + carOem + "]";
	}
	
}

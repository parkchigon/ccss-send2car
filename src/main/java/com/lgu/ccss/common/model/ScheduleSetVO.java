package com.lgu.ccss.common.model;


public class ScheduleSetVO {	
	private String scheduleId;
	private String membId;
	private String mgrappId;
	private String startNm;
	private String startAddress;
	private String startLonx;
	private String startLaty;
	private String targetNm;
	private String targetAddress;
	private String targetLonx;
	private String targetLaty;
	private String arrivHopeTime;
	private String repeatAlarmDay;
	private String useYn;
	
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	
	private String svrId;
	private String sendDt;
	
	private String targetRealLonx;
	private String targetRealLaty;
	private String targetPoiId;
	private String targetRoadName;
	private String targetRoadJibun;
	private String searchOption;
	
	private String rownum;
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
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
	public String getStartNm() {
		return startNm;
	}
	public void setStartNm(String startNm) {
		this.startNm = startNm;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getStartLonx() {
		return startLonx;
	}
	public void setStartLonx(String startLonx) {
		this.startLonx = startLonx;
	}
	public String getStartLaty() {
		return startLaty;
	}
	public void setStartLaty(String startLaty) {
		this.startLaty = startLaty;
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
	public String getRepeatAlarmDay() {
		return repeatAlarmDay;
	}
	public void setRepeatAlarmDay(String repeatAlarmDay) {
		this.repeatAlarmDay = repeatAlarmDay;
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
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
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
		return "ScheduleSetVO [scheduleId=" + scheduleId + ", membId=" + membId + ", mgrappId=" + mgrappId
				+ ", startNm=" + startNm + ", startAddress=" + startAddress + ", startLonx=" + startLonx
				+ ", startLaty=" + startLaty + ", targetNm=" + targetNm + ", targetAddress=" + targetAddress
				+ ", targetLonx=" + targetLonx + ", targetLaty=" + targetLaty + ", arrivHopeTime=" + arrivHopeTime
				+ ", repeatAlarmDay=" + repeatAlarmDay + ", useYn=" + useYn + ", regId=" + regId + ", regDt=" + regDt
				+ ", updId=" + updId + ", updDt=" + updDt + ", svrId=" + svrId + ", sendDt=" + sendDt
				+ ", targetRealLonx=" + targetRealLonx + ", targetRealLaty=" + targetRealLaty + ", targetPoiId="
				+ targetPoiId + ", targetRoadName=" + targetRoadName + ", targetRoadJibun=" + targetRoadJibun
				+ ", searchOption=" + searchOption + ", rownum=" + rownum + "]";
	}
	
}

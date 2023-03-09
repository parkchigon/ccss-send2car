package com.lgu.common.map.model.findStatRoute;

public class StatRouteJSON {	
	
	private String serviceType = "1";
	private String beforeCount = "1";
	private String afterCount = "1";
	private boolean routeInfoFlag = false;
	private String interval = "60";
	private String targetTime;
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getBeforeCount() {
		return beforeCount;
	}
	public void setBeforeCount(String beforeCount) {
		this.beforeCount = beforeCount;
	}
	public String getAfterCount() {
		return afterCount;
	}
	public void setAfterCount(String afterCount) {
		this.afterCount = afterCount;
	}
	public boolean isRouteInfoFlag() {
		return routeInfoFlag;
	}
	public void setRouteInfoFlag(boolean routeInfoFlag) {
		this.routeInfoFlag = routeInfoFlag;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}
	
	
	
}

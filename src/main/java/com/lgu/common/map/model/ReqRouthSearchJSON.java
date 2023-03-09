package com.lgu.common.map.model;

import java.util.List;

import com.lgu.common.map.model.route.LocDataJSON;



public class ReqRouthSearchJSON {	
	
	private String mrVersion;
	private String callBack;
	private String searchOption;
	private String carType;
	private String carHeight;
	private String carWeight;
	private String carWaterProtect;
	private LocDataJSON newStartloc;
	private LocDataJSON newEndloc;
	private List<LocDataJSON>  newVialocList;
	
	public String getMrVersion() {
		return mrVersion;
	}
	public void setMrVersion(String mrVersion) {
		this.mrVersion = mrVersion;
	}
	public String getCallBack() {
		return callBack;
	}
	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarHeight() {
		return carHeight;
	}
	public void setCarHeight(String carHeight) {
		this.carHeight = carHeight;
	}
	public String getCarWeight() {
		return carWeight;
	}
	public void setCarWeight(String carWeight) {
		this.carWeight = carWeight;
	}
	public String getCarWaterProtect() {
		return carWaterProtect;
	}
	public void setCarWaterProtect(String carWaterProtect) {
		this.carWaterProtect = carWaterProtect;
	}
	public LocDataJSON getNewStartloc() {
		return newStartloc;
	}
	public void setNewStartloc(LocDataJSON newStartloc) {
		this.newStartloc = newStartloc;
	}
	public LocDataJSON getNewEndloc() {
		return newEndloc;
	}
	public void setNewEndloc(LocDataJSON newEndloc) {
		this.newEndloc = newEndloc;
	}
	public List<LocDataJSON> getNewVialocList() {
		return newVialocList;
	}
	public void setNewVialocList(List<LocDataJSON> newVialocList) {
		this.newVialocList = newVialocList;
	}
	
	
}

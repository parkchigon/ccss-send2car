package com.lgu.common.map.model;

import java.util.List;

import com.lgu.common.map.model.route.LinkDataJSON;
import com.lgu.common.map.model.route.RGDataJSON;

public class ResRouthSearchResultJSON {	
	private String totalLength;
	private String toTalTime;
	private String totalFare;
	private String highwayDist;
	private String cityhighwayDist;
	private String nationalroadDist;
	private String localroadDist;
	private String roadDist;
	private String ferryDist;
	private String motorwayDist;
	private int linkCount;
	private List<LinkDataJSON> link;
	private int RGCount ;
	private List<RGDataJSON> RG;
	private String rid;
	
	
	public String getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}
	
	public String getToTalTime() {
		return toTalTime;
	}
	public void setToTalTime(String toTalTime) {
		this.toTalTime = toTalTime;
	}
	public String getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(String totalFare) {
		this.totalFare = totalFare;
	}
	public String getHighwayDist() {
		return highwayDist;
	}
	public void setHighwayDist(String highwayDist) {
		this.highwayDist = highwayDist;
	}
	public String getCityhighwayDist() {
		return cityhighwayDist;
	}
	public void setCityhighwayDist(String cityhighwayDist) {
		this.cityhighwayDist = cityhighwayDist;
	}
	public String getNationalroadDist() {
		return nationalroadDist;
	}
	public void setNationalroadDist(String nationalroadDist) {
		this.nationalroadDist = nationalroadDist;
	}
	public String getLocalroadDist() {
		return localroadDist;
	}
	public void setLocalroadDist(String localroadDist) {
		this.localroadDist = localroadDist;
	}
	public String getRoadDist() {
		return roadDist;
	}
	public void setRoadDist(String roadDist) {
		this.roadDist = roadDist;
	}
	public String getFerryDist() {
		return ferryDist;
	}
	public void setFerryDist(String ferryDist) {
		this.ferryDist = ferryDist;
	}
	public String getMotorwayDist() {
		return motorwayDist;
	}
	public void setMotorwayDist(String motorwayDist) {
		this.motorwayDist = motorwayDist;
	}
	public int getLinkCount() {
		return linkCount;
	}
	public void setLinkCount(int linkCount) {
		this.linkCount = linkCount;
	}
	public List<LinkDataJSON> getLink() {
		return link;
	}
	public void setLink(List<LinkDataJSON> link) {
		this.link = link;
	}
	public int getRGCount() {
		return RGCount;
	}
	public void setRGCount(int rGCount) {
		RGCount = rGCount;
	}
	public List<RGDataJSON> getRG() {
		return RG;
	}
	public void setRG(List<RGDataJSON> rG) {
		RG = rG;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	
	

}

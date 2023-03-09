package com.lgu.common.map.model;

import java.util.List;

import com.lgu.common.map.model.poi.POIDataJSON;
public class ResTargetSearchJSON extends ResErrorJSON{	
	
	private int totalcount;
	private int count;
	private int poitotalcount;
	private int poicount;
	private int tel_poitotalcount;
	private int tel_poicount;
	private int ucp_poitotalcount;
	private int ucp_poicount;
	private List<POIDataJSON> poi;
	private List<POIDataJSON> tel;
	private List<POIDataJSON> ucp;
	private String search_code;
	private String search_time;
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPoitotalcount() {
		return poitotalcount;
	}
	public void setPoitotalcount(int poitotalcount) {
		this.poitotalcount = poitotalcount;
	}
	public int getPoicount() {
		return poicount;
	}
	public void setPoicount(int poicount) {
		this.poicount = poicount;
	}
	public int getTel_poitotalcount() {
		return tel_poitotalcount;
	}
	public void setTel_poitotalcount(int tel_poitotalcount) {
		this.tel_poitotalcount = tel_poitotalcount;
	}
	public int getTel_poicount() {
		return tel_poicount;
	}
	public void setTel_poicount(int tel_poicount) {
		this.tel_poicount = tel_poicount;
	}
	public int getUcp_poitotalcount() {
		return ucp_poitotalcount;
	}
	public void setUcp_poitotalcount(int ucp_poitotalcount) {
		this.ucp_poitotalcount = ucp_poitotalcount;
	}
	public int getUcp_poicount() {
		return ucp_poicount;
	}
	public void setUcp_poicount(int ucp_poicount) {
		this.ucp_poicount = ucp_poicount;
	}
	public List<POIDataJSON> getPoi() {
		return poi;
	}
	public void setPoi(List<POIDataJSON> poi) {
		this.poi = poi;
	}
	public List<POIDataJSON> getTel() {
		return tel;
	}
	public void setTel(List<POIDataJSON> tel) {
		this.tel = tel;
	}
	public List<POIDataJSON> getUcp() {
		return ucp;
	}
	public void setUcp(List<POIDataJSON> ucp) {
		this.ucp = ucp;
	}
	public String getSearch_code() {
		return search_code;
	}
	public void setSearch_code(String search_code) {
		this.search_code = search_code;
	}
	public String getSearch_time() {
		return search_time;
	}
	public void setSearch_time(String search_time) {
		this.search_time = search_time;
	}
	
	
	
}

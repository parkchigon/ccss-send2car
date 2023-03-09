package com.lgu.common.map.model.poi;

import java.util.List;

public class SubPOIDataJSON {	
	
	private int count;	
	private List<POIDataJSON> poi;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<POIDataJSON> getPoi() {
		return poi;
	}
	public void setPoi(List<POIDataJSON> poi) {
		this.poi = poi;
	}
	
}

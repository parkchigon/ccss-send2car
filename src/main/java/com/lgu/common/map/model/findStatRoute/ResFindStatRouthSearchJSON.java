package com.lgu.common.map.model.findStatRoute;

import java.util.List;

import com.lgu.common.map.model.ResErrorJSON;
import com.lgu.common.map.model.findStatRoute.TimeListJSON;


public class ResFindStatRouthSearchJSON extends ResErrorJSON{	
	public static final String SUCCESS_TLO_CODE = "20000000";
	
	private List<TimeListJSON> time_list;
	private String route_time;
	private int time_count;
	private String route_code;
	
	public List<TimeListJSON> getTime_list() {
		return time_list;
	}
	public void setTime_list(List<TimeListJSON> time_list) {
		this.time_list = time_list;
	}
	public String getRoute_time() {
		return route_time;
	}
	public void setRoute_time(String route_time) {
		this.route_time = route_time;
	}
	public int getTime_count() {
		return time_count;
	}
	public void setTime_count(int time_count) {
		this.time_count = time_count;
	}
	public String getRoute_code() {
		return route_code;
	}
	public void setRoute_code(String route_code) {
		this.route_code = route_code;
	}
	

}

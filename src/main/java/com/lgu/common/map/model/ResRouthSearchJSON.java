package com.lgu.common.map.model;

public class ResRouthSearchJSON extends ResErrorJSON{	
	private ResRouthSearchResultJSON result;
	private String route_time;
	private String cdn_time;
	private String guide_time;
	private String route_code;
	private String cdn_code;
	public ResRouthSearchResultJSON getResult() {
		return result;
	}

	public void setResult(ResRouthSearchResultJSON result) {
		this.result = result;
	}

	public String getRoute_time() {
		return route_time;
	}

	public void setRoute_time(String route_time) {
		this.route_time = route_time;
	}

	public String getCdn_time() {
		return cdn_time;
	}

	public void setCdn_time(String cdn_time) {
		this.cdn_time = cdn_time;
	}

	public String getGuide_time() {
		return guide_time;
	}

	public void setGuide_time(String guide_time) {
		this.guide_time = guide_time;
	}

	public String getRoute_code() {
		return route_code;
	}

	public void setRoute_code(String route_code) {
		this.route_code = route_code;
	}

	public String getCdn_code() {
		return cdn_code;
	}

	public void setCdn_code(String cdn_code) {
		this.cdn_code = cdn_code;
	}
	
	

}

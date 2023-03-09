package com.lgu.ccss.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Send2CarJSON {
	private String id;
	private String address;
	private String lonx;
	private String laty;
	private String kkosender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLonx() {
		return lonx;
	}
	public void setLonx(String lonx) {
		this.lonx = lonx;
	}
	public String getLaty() {
		return laty;
	}
	public void setLaty(String laty) {
		this.laty = laty;
	}
	public String getKkosender() {
		return kkosender;
	}
	public void setKkosender(String kkosender) {
		this.kkosender = kkosender;
	}
	
	@Override
	public String toString() {
		return "Send2CarJSON [id=" + id + ", address=" + address + ", lonx=" + lonx + ", laty=" + laty + ", kkosender="
				+ kkosender + "]";
	}
}
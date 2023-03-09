package com.lgu.common.map.model;

import java.util.List;

public class ResTotalSearchJSON extends ResErrorJSON{	
	
	private int admtotalcount;
	private int admcount;
	private List<AdmJSON> adm;
	public int getAdmtotalcount() {
		return admtotalcount;
	}
	public void setAdmtotalcount(int admtotalcount) {
		this.admtotalcount = admtotalcount;
	}
	public int getAdmcount() {
		return admcount;
	}
	public void setAdmcount(int admcount) {
		this.admcount = admcount;
	}
	public List<AdmJSON> getAdm() {
		return adm;
	}
	public void setAdm(List<AdmJSON> adm) {
		this.adm = adm;
	}
	
	
}

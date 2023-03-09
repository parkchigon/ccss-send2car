package com.lgu.ccss.send2car.model;

import java.io.Serializable;

public class SyncVO implements Serializable {

	static final long serialVersionUID = -6925626454717170883L;
	
	private String oracle;
	private String altibase;
	
	public String getOracle() {
		return oracle;
	}
	public void setOracle(String oracle) {
		this.oracle = oracle;
	}
	public String getAltibase() {
		return altibase;
	}
	public void setAltibase(String altibase) {
		this.altibase = altibase;
	}
	@Override
	public String toString() {
		return "SampleVO [oracle=" + oracle + ", altibase=" + altibase + "]";
	}
}

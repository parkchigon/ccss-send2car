package com.lgu.ccss.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MqttContentJSON {
	private String code;
	private String txt;
	private Send2CarJSON send2car;
	private InfoAppJSON infoapp;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Send2CarJSON getSend2car() {
		return send2car;
	}
	public void setSend2car(Send2CarJSON send2car) {
		this.send2car = send2car;
	}
	public InfoAppJSON getInfoapp() {
		return infoapp;
	}
	public void setInfoapp(InfoAppJSON infoapp) {
		this.infoapp = infoapp;
	}
	
	@Override
	public String toString() {
		return "MqttContentJSON [code=" + code + ", txt=" + txt + ", send2car=" + send2car + ", infoapp=" + infoapp
				+ "]";
	}
}
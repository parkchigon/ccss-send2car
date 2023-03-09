package com.lgu.ccss.common.model.setInfo;

import java.io.IOException;

import com.lgu.common.util.JsonUtil;



public class JsonSetInfo {
	
	private MsgSetInfo message;
	
	private NotiSetInfo notifications;
	
	public JsonSetInfo(){
		this.message = new MsgSetInfo();
		this.notifications = new NotiSetInfo();
	}
	
	public MsgSetInfo getMessage() {
		return message;
	}
	public void setMessage(MsgSetInfo message) {
		this.message = message;
	}
	public NotiSetInfo getNotifications() {
		return notifications;
	}
	public void setNotifications(NotiSetInfo notifications) {
		this.notifications = notifications;
	}
	
	public static void main(String[] args) {
		JsonSetInfo jsonSetInfo = new JsonSetInfo();
		
		try {
			System.out.println(JsonUtil.marshallingJson(jsonSetInfo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

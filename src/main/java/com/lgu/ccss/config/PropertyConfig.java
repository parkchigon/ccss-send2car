package com.lgu.ccss.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {
		
	@Value("#{config['esb.expiredsms.duration']}")
	private String expSMSDuration;

	@Value("#{config['esb.expiredsms.sendtime']}")
	private String expSMSSendTime;
	
	@Value("#{config['esb.url']}")
	private String esbUrl;

	@Value("#{config['esb.systemid']}")
	private String esbSystemID;
	
	@Value("#{config['esb.expiredsms.text']}")
	private String esbSMSText;
	
	@Value("#{config['tlo.config.path']}")
	private String tloConfigPath;

	@Value("#{config['ai.svrKey']}")
	private String aiSvrKey;
	
	@Value("#{config['ai.auth.url']}")
	private String aiAuthUrl;
	
	@Value("#{config['ai.weather.connection.timeout']}")
	private String aiAuthConnectionTimeout;
	
	@Value("#{config['ai.auth.timeout']}")
	private String aiAuthTimeout;
	
	@Value("#{config['esb.expiredreserve.duration']}")
	private String esbExpireDuration;
	
	@Value("#{config['esb.expiredreserve.sendtime']}")
	private String esbExpireSendtime;
	
	
	public String getExpSMSDuration() {
		return expSMSDuration;
	}

	public void setExpSMSDuration(String expSMSDuration) {
		this.expSMSDuration = expSMSDuration;
	}

	public String getExpSMSSendTime() {
		return expSMSSendTime;
	}

	public void setExpSMSSendTime(String expSMSSendTime) {
		this.expSMSSendTime = expSMSSendTime;
	}

	public String getEsbUrl() {
		return esbUrl;
	}

	public void setEsbUrl(String esbUrl) {
		this.esbUrl = esbUrl;
	}

	public String getEsbSystemID() {
		return esbSystemID;
	}

	public void setEsbSystemID(String esbSystemID) {
		this.esbSystemID = esbSystemID;
	}

	public String getEsbSMSText() {
		return esbSMSText;
	}

	public void setEsbSMSText(String esbSMSText) {
		this.esbSMSText = esbSMSText;
	}

	public String getTloConfigPath() {
		return tloConfigPath;
	}

	public void setTloConfigPath(String tloConfigPath) {
		this.tloConfigPath = tloConfigPath;
	}

	public String getAiSvrKey() {
		return aiSvrKey;
	}

	public void setAiSvrKey(String aiSvrKey) {
		this.aiSvrKey = aiSvrKey;
	}

	public String getAiAuthUrl() {
		return aiAuthUrl;
	}

	public void setAiAuthUrl(String aiAuthUrl) {
		this.aiAuthUrl = aiAuthUrl;
	}

	public String getAiAuthConnectionTimeout() {
		return aiAuthConnectionTimeout;
	}

	public void setAiAuthConnectionTimeout(String aiAuthConnectionTimeout) {
		this.aiAuthConnectionTimeout = aiAuthConnectionTimeout;
	}

	public String getAiAuthTimeout() {
		return aiAuthTimeout;
	}

	public void setAiAuthTimeout(String aiAuthTimeout) {
		this.aiAuthTimeout = aiAuthTimeout;
	}

	public String getEsbExpireDuration() {
		return esbExpireDuration;
	}

	public void setEsbExpireDuration(String esbExpireDuration) {
		this.esbExpireDuration = esbExpireDuration;
	}

	public String getEsbExpireSendtime() {
		return esbExpireSendtime;
	}

	public void setEsbExpireSendtime(String esbExpireSendtime) {
		this.esbExpireSendtime = esbExpireSendtime;
	}
	
	
	
}

package com.lgu.ccss.send2car.service.worker;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.ccss.App;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.common.tlo.TloWriter;

public class TLOImpl {
	private final static Logger logger = LoggerFactory.getLogger(TLOImpl.class);
	
	public static void initTLO(String ctn, String membID)
	{
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, "SVC");
		try {
			tlo.put(TloData.CLIENT_IP,  System.getProperty("SERVER_IP"));
		} catch (Exception e) {
			logger.error("Exception", e);
			
			tlo.put(TloData.CLIENT_IP,  "127.0.0.1");
		}
		
		tlo.put(TloData.SVC_NAME, "CCS");
		tlo.put(TloData.SID, ctn);
		tlo.put(TloData.SERVER_ID, App.serverID);
		tlo.put(TloData.MEMB_ID, membID);
		tlo.put(TloData.NCAS_SVC_CLASS, "NOO1");
		
		TloUtil.setTloData(ctn,tlo);
	}
	
	public static void writeTLO(String ctn)
	{
		if( ctn == null )
		{
			return;
		}
		
		TloWriter tloWriter = new TloWriter();
		tloWriter.write(TloUtil.removeTloData(ctn));
	}
}

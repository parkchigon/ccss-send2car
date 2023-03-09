package com.lgu.common.tlo;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TloWriter {

	private static Logger logger = LoggerFactory.getLogger(TloWriter.class);
	
	private static final Map<String, String> tloFieldMap = new LinkedHashMap<String, String>();
	
	public static String tloFilePath = null;
	
	// TLO 로그 기록
	synchronized public boolean write(Map<String, String> tloMap) {
		
		StringBuffer sb = new StringBuffer();
		TUnionLog tUnionLog = new TUnionLog(TloWriter.tloFilePath);
		tUnionLog.startTran(tUnionLog.guidKey(), sb);
		
		logger.debug("tloFieldMap" + tloFieldMap);
		logger.debug("tloMap" + tloMap);

		for (String key : tloFieldMap.keySet()) {
			String value = tloMap.get(key);
			if (value == null) {
				value = "";
			}
			tUnionLog.setElement(key, value, sb);
		}
		
		// TLO로그 생성 실패 시 1회 재시도 한다.
		int result = tUnionLog.endTran(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), sb);
		if (result == 0) {
			result = tUnionLog.endTran(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), sb);
		}
		
		//apiTlologger.trace(sb.toString());

		return result == 1 ? true : false;
	}
	
	public static void setTloFieldMap(Field [] fields) {
		for (Field field : fields) {
			tloFieldMap.put(field.getName(), "dummy");
		}
	}
	
	public static String getTloFieldValue(String key) {
		return tloFieldMap.get(key);
	}
	
	public static int getTloFieldSize() {
		return tloFieldMap.size();
	}
}

package com.lgu.ccss.common.tlo;

import java.util.HashMap;
import java.util.Map;


import com.lgu.common.collection.ExpiringMap;
import com.lgu.common.tlo.TloWriter;

public class TloUtil {
	
	private static final Map<String, String> svcClassMap;
	private static ExpiringMap<String, Map<String, String>> TLO_LIST_MAP = null;
	
	static {
		svcClassMap = getSvcClassMap();
		TLO_LIST_MAP = new ExpiringMap<String, Map<String, String>>();
		
		TLO_LIST_MAP.addExpirationListener(new TloExpiredMapListner<>());
	}
	
	public static void setTloData(String tloKey, Map<String, String> tloMap) {
		
		if (TloWriter.getTloFieldSize() == 0) {
			TloWriter.setTloFieldMap(TloData.class.getDeclaredFields());
		}
		
		String exist = null;
		
		Map<String, String> existTLOMap = TLO_LIST_MAP.get(tloKey);
		
		if( existTLOMap == null )
			existTLOMap = new HashMap<String, String>();
		
		for (String key : tloMap.keySet()) {
			try {
				exist = TloWriter.getTloFieldValue(key);
			} catch (NullPointerException e) {
				
			}
			
			if (exist != null) {
				existTLOMap.put(key, tloMap.get(key));
			}
		}
		
		TLO_LIST_MAP.put(tloKey, existTLOMap);
	}
	
	public static Map<String, String> removeTloData(String tloKey) {
		Map<String, String> existTLOMap = TLO_LIST_MAP.remove(tloKey);
		
		if( existTLOMap == null )
			return null;
		else
			return existTLOMap;
	}
	
	public static Map<String, String> getSvcClassMap() {
		Map<String, String> svcClassMap = new HashMap<String, String>();
		
		return svcClassMap;
	}
	
	public static String getSvcClass(String key) {
		return svcClassMap.get(key);
	}
}
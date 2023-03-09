package com.lgu.common.map;

public class MapConst {
	
	public final static String URL_POI_NAME_SEARCH	= "/poi/searchPoiNumber";
	public final static String URL_ROUTH_SEARCH	= "/routeplan/newSearchRouteListforWeb"; 
	public final static String URL_REV_GEOCODING_SEARCH	= "/poi/revgeocoding"; 
	public final static String URL_TOTAL_SEARCH	= "/poi/searchSQE"; 
	public final static String URL_FIND_STAT_ROUTH	= "/routeplan/findStatRoute"; 
	public final static String URL_DOWNLOAD_MAP_INFO= "/map/mapDownloadInfo";
	
	public final static String DEVICE_TYPE_AM = "AM";
	public final static String DEVICE_TYPE_BM = "BM";
	public final static String DEF_URL= "URL";
	
	
	//S:MapInfra Header Const
	public static final String HD_NAME_API_VERSION="apiVersion";  //M
	public static final String HD_NAME_API_TYPE="apiType";	//M
	public static final String HD_NAME_CLIENT_IP= "clientIp";
	public static final String HD_NAME_DEV_INFO="devInfo";	//M
	public static final String HD_NAME_OS_INFO="osInfo";
	public static final String HD_NAME_NW_INFO="nwInfo";
	public static final String HD_NAME_DEV_MODEL="devModel";
	public static final String HD_NAME_CARRIER_TYPE="carrierType";
	public static final String HD_NAME_TEL_NO="telNo";		//C:핸드폰의 경우 필수(CTN) 
	public static final String HD_NAME_AUTH_KEY="authKey"; //M
	public static final String HD_NAME_SVC_ID="svcId";	  //M
	public static final String HD_NAME_X_COORD="xCoord";   //O:핸드폰의 경우 필수(CTN) 
	public static final String HD_NAME_Y_COORD="yCoord";   //O:핸드폰의 경우 필수(CTN) 
	public static final String HD_NAME_SPEED="speed";
	public static final String HD_NAME_GPS_TIME="gpsTime";
	public static final String HD_NAME_VALID_YN="validYn";
		
		
	//E:MapInfra Header Const
	public final static String HD_VALUE_CONTENTTYPE_JSON_UTF8 = "application/json;charset=UTF-8";
	
	public final static String JSON_VALUE_IDTYPECD = "CTN";
	
	public final static String TRACE_SOURCE = "WAS";
	public final static String TRACE_TARGET = "MAP_INFRA";
	
	public final static String SVC_MAP="MAP";
	public final static String SVC_POI="POI";
	public final static String SVC_ROUTH="ROUTH";
	public final static String SVC_LOCATION="LOCATION";
	
	public final static String API_VERSION="1.0.0";
	
	public final static String API_TYPE_OPEN_API = "01";
	public final static String API_TYPE_RELAY_API = "02";
	public final static String API_TYPE_LOG_API = "03";
	
	public final static String DEV_INFO_PHONE = "01";
	public final static String DEV_INFO_PAD = "02"; 
	public final static String DEV_INFO_PC = "03"; 
	public final static String DEV_INFO_TV = "04"; 
	public final static String DEV_INFO_SET_TOP = "05"; 
	
	
	public final static String RESULT_SUCCESS = "0";
		
}

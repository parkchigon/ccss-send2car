package com.lgu.common.map;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.common.map.model.ReqRouthSearchJSON;
import com.lgu.common.map.model.ResRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.ExtensionDataJSON;
import com.lgu.common.map.model.findStatRoute.ReqFindStatRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResFindStatRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResMapDownloadInfoJSON;
import com.lgu.common.map.model.findStatRoute.StatRouteJSON;
import com.lgu.common.map.model.route.LocDataJSON;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.AES256Util;

@Component
public class MapAgent {
	private static final Logger logger = LoggerFactory.getLogger(MapAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['map.infra.encrypt.key']}")
	private String mapEncryptKey;

	@Value("#{config['am.map.infra.url']}")
	private String amMapInfraUrl;
	
	@Value("#{config['bm.map.infra.url']}")
	private String bmMapInfraUrl;

	@Value("#{config['am.map.infra.svc.id']}")
	private String amMapInfraSvcId;

	@Value("#{config['am.mapapi.auth.key']}")
	private String amMapapiAuthKey;
	@Value("#{config['am.routh.auth.key']}")
	private String amRouthAuthKey;
	@Value("#{config['am.poi.auth.key']}")
	private String amPoiAuthKey;
	@Value("#{config['am.location.auth.key']}")
	private String amLocationAuthKey;

	@Value("#{config['bm.map.infra.svc.id']}")
	private String bmMapInfraSvcId;

	@Value("#{config['bm.mapapi.auth.key']}")
	private String bmMapapiAuthKey;
	@Value("#{config['bm.routh.auth.key']}")
	private String bmRouthAuthKey;
	@Value("#{config['bm.poi.auth.key']}")
	private String bmPoiAuthKey;
	@Value("#{config['bm.location.auth.key']}")
	private String bmLocationAuthKey;

	@Value("#{config['map.infra.connection.timeout']}")
	private String mapInfraConnectionTimeout;

	@Value("#{config['map.infra.timeout']}")
	private String mapInfraTimeout;

	@Value("#{config['map.infra.mrVersion']}")
	private String mapInfraMrverSion;

	@Value("#{config['map.proxy.http.proxyHost']}")
	private String mapHttpProxyHost;
	
	@Value("#{config['map.proxy.http.proxyPort']}")
	private int mapHttpProxyPort;
	
	@Value("#{config['map.proxy.http.proxySubPort']}")
	private String mapHttpProxySubHost;
	
	@Autowired
	private RestAgent restAgent;

	public RestRequestData makeHeaderData(String url, Map<String, String> svcIdAuthKeyMap, ScheduleSetVO scheduleVO) {
		// set headers
		RestRequestData reqData = new RestRequestData(url);
		reqData.setHeader(MapConst.HD_NAME_API_VERSION, MapConst.API_VERSION); // M
		reqData.setHeader(MapConst.HD_NAME_API_TYPE, MapConst.API_TYPE_OPEN_API); // M
		reqData.setHeader(MapConst.HD_NAME_CLIENT_IP, "");
		reqData.setHeader(MapConst.HD_NAME_DEV_INFO, MapConst.DEV_INFO_PHONE); // M
		reqData.setHeader(MapConst.HD_NAME_OS_INFO, "");
		reqData.setHeader(MapConst.HD_NAME_NW_INFO, "");
		reqData.setHeader(MapConst.HD_NAME_DEV_MODEL, "");
		reqData.setHeader(MapConst.HD_NAME_CARRIER_TYPE, "");
		reqData.setHeader(MapConst.HD_NAME_TEL_NO, ""); // C:핸드폰의 경우 필수(CTN)
		reqData.setHeader(MapConst.HD_NAME_AUTH_KEY, svcIdAuthKeyMap.get(MapConst.HD_NAME_AUTH_KEY)); // M
		reqData.setHeader(MapConst.HD_NAME_SVC_ID, svcIdAuthKeyMap.get(MapConst.HD_NAME_SVC_ID)); // M

		/*
		String xCoord = (String) reqJson.getParam().get(RequestJSON.PARAM_X_CORRD);
		if (xCoord != null && xCoord.length() > 0) {
			reqData.setHeader(MapConst.HD_NAME_X_COORD, xCoord); // O:핸드폰의 경우 필수(CTN)
		} else {
			reqData.setHeader(MapConst.HD_NAME_X_COORD, "");
		}
		String yCoord = (String) reqJson.getParam().get(RequestJSON.PARAM_Y_CORRD);
		if (yCoord != null && yCoord.length() > 0) {
			reqData.setHeader(MapConst.HD_NAME_Y_COORD, yCoord); // O:핸드폰의 경우 필수(CTN)
		} else {
			reqData.setHeader(MapConst.HD_NAME_Y_COORD, "");
		}
		*/
		reqData.setHeader(MapConst.HD_NAME_X_COORD, "");
		reqData.setHeader(MapConst.HD_NAME_Y_COORD, "");
		reqData.setHeader(MapConst.HD_NAME_SPEED, "");
		reqData.setHeader(MapConst.HD_NAME_GPS_TIME, "");
		reqData.setHeader(MapConst.HD_NAME_VALID_YN, "");

		reqData.setHeader(HTTP.CONTENT_TYPE, MapConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		return reqData;
	}

	public ResRouthSearchJSON routeSearch(String subUrl, String svcType, String deviceType, String deviceCtn,
			ScheduleSetVO scheduleVO) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		// get Terget Service Key Map
		Map<String, String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType, svcType);

		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;

		// set headers
		RestRequestData reqData = makeHeaderData(url, svcIdAuthKeyMap, scheduleVO);

		// set body
		ReqRouthSearchJSON reqRouthSearchJSON = new ReqRouthSearchJSON();

		reqRouthSearchJSON.setMrVersion(mapInfraMrverSion);
		reqRouthSearchJSON.setCallBack("func_cb");
		reqRouthSearchJSON.setSearchOption("real_traffic"); // real_traffic : 빠른길 real_traffic2 : 편한길
															// real_traffic_freeroad :무료도로 short_distance_priority :최단거리
															// highway_priority :고속도로우선 motorcycle :이륜차 이용도로
		reqRouthSearchJSON.setCarType("");
		reqRouthSearchJSON.setCarHeight("");
		reqRouthSearchJSON.setCarWeight("");
		reqRouthSearchJSON.setCarWaterProtect("");

		LocDataJSON startLocData = new LocDataJSON();
		startLocData.setLonx(AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getStartLonx()));
		startLocData.setLaty(AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getStartLaty()));
		startLocData.setName(scheduleVO.getStartNm());
		reqRouthSearchJSON.setNewStartloc(startLocData);

		LocDataJSON endLocData = new LocDataJSON();
		endLocData.setLonx(AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getTargetLonx()));
		endLocData.setLaty(AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getTargetLaty()));
		endLocData.setName(scheduleVO.getTargetNm());
		reqRouthSearchJSON.setNewEndloc(endLocData);

		reqData.setJson(gson.toJson(reqRouthSearchJSON));

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

		return routeSearchMapApiRequest(reqData, deviceCtn);
	}

	private ResRouthSearchJSON routeSearchMapApiRequest(RestRequestData reqData, String deviceCtn) {

		RestResultData resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}

		ResRouthSearchJSON resRouthSearchJSON = new ResRouthSearchJSON();
		try {
			resRouthSearchJSON = gson.fromJson(resultData.getJson(), ResRouthSearchJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), e);
			return null;
		}

		return resRouthSearchJSON;
	}
	
	public ResFindStatRouthSearchJSON findStatRouthSearch(String subUrl, String svcType, String deviceType, String deviceCtn,
			ScheduleSetVO scheduleVO, String arrivHopeTime) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(url, svcIdAuthKeyMap, scheduleVO);
		
		// set body
		ReqFindStatRouthSearchJSON reqFindStatRouthSearchJSON = new ReqFindStatRouthSearchJSON();
		
		//지도 VER 조회 smr_ver
		ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();
		
		resMapDownloadInfoJSON =	mapDownloadInfo(url,MapConst.SVC_MAP,deviceType); 
		if(resMapDownloadInfoJSON !=null) {
			
		
		reqFindStatRouthSearchJSON.setMrVersion(resMapDownloadInfoJSON.getSmr_ver());
		reqFindStatRouthSearchJSON.setSearchOption("real_traffic"); //real_traffic : 빠른길 real_traffic2 : 편한길 real_traffic_freeroad :무료도로 short_distance_priority :최단거리 highway_priority :고속도로우선  motorcycle :이륜차 이용도로
		reqFindStatRouthSearchJSON.setSearchType("7"); // 7 : 통계 탐색
	
		//Start Set
		com.lgu.common.map.model.findStatRoute.LocDataJSON startLocData = new com.lgu.common.map.model.findStatRoute.LocDataJSON();
		String startLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getStartLonx());
		String startlaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getStartLaty());
		startLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble(startLonx) * 36000)))).replace(".",""));
		startLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble(startlaty) * 36000)))).replace(".",""));
		startLocData.setName(scheduleVO.getStartNm());
		reqFindStatRouthSearchJSON.setNewStartloc(startLocData);
				
		//Target Set
		com.lgu.common.map.model.findStatRoute.LocDataJSON endLocData = new com.lgu.common.map.model.findStatRoute.LocDataJSON();
		String endLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getTargetLonx());
		String endLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, scheduleVO.getTargetLaty());
		endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble(endLonx) * 36000)))).replace(".",""));
		endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble(endLaty) * 36000)))).replace(".",""));
		endLocData.setName(scheduleVO.getTargetNm());
		reqFindStatRouthSearchJSON.setNewEndloc(endLocData);		
		
		//ExtensionData Set : arrivHomeTime set
		List<ExtensionDataJSON> extensionDataJSONList = new ArrayList<ExtensionDataJSON>();
		ExtensionDataJSON extensionDataJSON = new ExtensionDataJSON();
		/*System.out.println("#########");
		String arrivHopeTime = (String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME);
		System.out.println("arrivHopeTime:" + arrivHopeTime);
		System.out.println("#########");*/
		
		String targetTime = String.valueOf(convertDateformatToLong("yyyy-MM-dd HH:mm:ss" , arrivHopeTime));
		
		StatRouteJSON statRouteJSON = new StatRouteJSON();
		statRouteJSON.setTargetTime(targetTime);
		extensionDataJSON.setStatRoute(statRouteJSON);
		extensionDataJSONList.add(extensionDataJSON);
		reqFindStatRouthSearchJSON.setNewExtensionList(extensionDataJSONList);
		
		reqData.setJson(gson.toJson(reqFindStatRouthSearchJSON));

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

		return findStatRouthSearchMapApiRequest(reqData, deviceCtn);
		}else {
			logger.error("failed to request Map Infra Server - mapDownloadInfo");
			return null;
		}
	}
	
	private ResFindStatRouthSearchJSON findStatRouthSearchMapApiRequest(RestRequestData reqData, String mgrappCtn) {
		
		RestResultData resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappCtn({})", mgrappCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappCtn({}) statusCode({})", mgrappCtn, resultData.getStatusCode());
			return null;
		}

		ResFindStatRouthSearchJSON resFindStatRouthSearchJSON = new ResFindStatRouthSearchJSON();
		try {
			resFindStatRouthSearchJSON = gson.fromJson(resultData.getJson(), ResFindStatRouthSearchJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		
		return resFindStatRouthSearchJSON;
	}

	public Map<String, String> getMapSvcIdAndAuthKey(String deviceType, String svcType) {

		Map<String, String> result = new HashMap<String, String>();

		String svcId;
		String authKey;
		String reqUrl;
		
		// set svc_id Value
		if (deviceType.equals("AM")) {
			svcId = amMapInfraSvcId;
			// set authKey
			if (svcType.equals(MapConst.SVC_MAP)) {
				authKey = amMapapiAuthKey;
			} else if (svcType.equals(MapConst.SVC_POI)) {
				authKey = amPoiAuthKey;
			} else if (svcType.equals(MapConst.SVC_ROUTH)) {
				authKey = amRouthAuthKey;
			} else { // LOCATION
				authKey = amLocationAuthKey;
			}
			reqUrl = amMapInfraUrl;
			
		} else {
			svcId = bmMapInfraSvcId;
			// set authKey
			if (svcType.equals(MapConst.SVC_MAP)) {
				authKey = bmMapapiAuthKey;
			} else if (svcType.equals(MapConst.SVC_POI)) {
				authKey = bmPoiAuthKey;
			} else if (svcType.equals(MapConst.SVC_ROUTH)) {
				authKey = bmRouthAuthKey;
			} else { // LOCATION
				authKey = bmLocationAuthKey;
			}
			reqUrl = bmMapInfraUrl;
		}
		
		result.put(MapConst.HD_NAME_SVC_ID, svcId);
		result.put(MapConst.HD_NAME_AUTH_KEY, authKey);
		result.put(MapConst.DEF_URL, reqUrl);
		
		return result;

	}

	private void setTraceInfo(RestRequestData reqData, String mgrappCtn) {
		reqData.setTraceId(mgrappCtn);
		reqData.setSource(MapConst.TRACE_SOURCE);
		reqData.setTarget(MapConst.TRACE_TARGET);
	}
	
	public ResMapDownloadInfoJSON mapDownloadInfo(String uri,String svcType,String deviceType) {
		
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  MapConst.URL_DOWNLOAD_MAP_INFO;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(url,svcIdAuthKeyMap,null);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));
		
		
		return mapDownLoadInfoRequestApi(reqData);
	}
	
	private ResMapDownloadInfoJSON mapDownLoadInfoRequestApi(RestRequestData reqData) {
		
		RestResultData resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. ");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server.  statusCode({})", resultData.getStatusCode());
			return null;
		}

		ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();
		try {
			resMapDownloadInfoJSON = gson.fromJson(resultData.getJson(), ResMapDownloadInfoJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		
		return resMapDownloadInfoJSON;
	}
	
	public long convertDateformatToLong(String dateFormat,String arrivHopeTime){
		
		SimpleDateFormat df = new SimpleDateFormat(dateFormat); //yyyy-MM-dd HH:mm
		
		long time=0;
		
		try {
			Date date;
			date = df.parse(arrivHopeTime);
			time = date.getTime() / 1000;
				
		} catch (ParseException e) {
			
			logger.error("arrivHopeTime convertDateformatToLong Fail",e);
		}
		System.out.println("Time:" + time);
		return time;
	}
}

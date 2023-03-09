package com.lgu.common.map.model.findStatRoute;

public class ExtensionDataJSON {	
	
	private String headerType ="4"; //1 다중 목적지 최적 탐색 / 2: 지나간 톨게이트 /3: 사용자 정보 / 4: 통계 탐색  5: 경유도로 / 6: 유고 회피 / 7: 경로 회피
	private StatRouteJSON statRoute;
	
	public String getHeaderType() {
		return headerType;
	}
	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}
	public StatRouteJSON getStatRoute() {
		return statRoute;
	}
	public void setStatRoute(StatRouteJSON statRoute) {
		this.statRoute = statRoute;
	}
	
	
}

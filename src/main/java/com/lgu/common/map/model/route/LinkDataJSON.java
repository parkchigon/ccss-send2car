package com.lgu.common.map.model.route;

import java.util.List;

public class LinkDataJSON {	
	private String linkLength;
	private String linkTime;   
	private String linkName;
	private String linkType;
	private String linkFacility;
	private String linkSpeedTI;
	private String linkSpeedDefault;
	private int restLengthToEnd ;
	private String restTimeToEnd;
	private String vertexCount;
	private List<VertexDataJSON> vertex;
	public String getLinkLength() {
		return linkLength;
	}
	public void setLinkLength(String linkLength) {
		this.linkLength = linkLength;
	}
	public String getLinkTime() {
		return linkTime;
	}
	public void setLinkTime(String linkTime) {
		this.linkTime = linkTime;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getLinkFacility() {
		return linkFacility;
	}
	public void setLinkFacility(String linkFacility) {
		this.linkFacility = linkFacility;
	}
	public String getLinkSpeedTI() {
		return linkSpeedTI;
	}
	public void setLinkSpeedTI(String linkSpeedTI) {
		this.linkSpeedTI = linkSpeedTI;
	}
	public String getLinkSpeedDefault() {
		return linkSpeedDefault;
	}
	public void setLinkSpeedDefault(String linkSpeedDefault) {
		this.linkSpeedDefault = linkSpeedDefault;
	}
	public int getRestLengthToEnd() {
		return restLengthToEnd;
	}
	public void setRestLengthToEnd(int restLengthToEnd) {
		this.restLengthToEnd = restLengthToEnd;
	}
	public String getRestTimeToEnd() {
		return restTimeToEnd;
	}
	public void setRestTimeToEnd(String restTimeToEnd) {
		this.restTimeToEnd = restTimeToEnd;
	}
	public String getVertexCount() {
		return vertexCount;
	}
	public void setVertexCount(String vertexCount) {
		this.vertexCount = vertexCount;
	}
	public List<VertexDataJSON> getVertex() {
		return vertex;
	}
	public void setVertex(List<VertexDataJSON> vertex) {
		this.vertex = vertex;
	}
	
	
}

package com.lgu.common.map.model.findStatRoute;

public class LocDataJSON {	
	
	private String lonx;
	private String laty;
	private String name;
	private String mapid = "-1";
	private String linkid = "-1";
	private String nodeid = "-1";
	private String direction = "-1";
	private boolean usingName = false;
	private boolean usingHideViaLoc = true;
	private boolean viaLocStart = false;
	private boolean viaLocEnd = false;
	public String getLonx() {
		return lonx;
	}
	public void setLonx(String lonx) {
		this.lonx = lonx;
	}
	public String getLaty() {
		return laty;
	}
	public void setLaty(String laty) {
		this.laty = laty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public boolean isUsingName() {
		return usingName;
	}
	public void setUsingName(boolean usingName) {
		this.usingName = usingName;
	}
	public boolean isUsingHideViaLoc() {
		return usingHideViaLoc;
	}
	public void setUsingHideViaLoc(boolean usingHideViaLoc) {
		this.usingHideViaLoc = usingHideViaLoc;
	}
	public boolean isViaLocStart() {
		return viaLocStart;
	}
	public void setViaLocStart(boolean viaLocStart) {
		this.viaLocStart = viaLocStart;
	}
	public boolean isViaLocEnd() {
		return viaLocEnd;
	}
	public void setViaLocEnd(boolean viaLocEnd) {
		this.viaLocEnd = viaLocEnd;
	}
}

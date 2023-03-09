package com.lgu.common.map.model.findStatRoute;

import java.util.List;

import com.lgu.common.map.model.findStatRoute.ExtensionDataJSON;
import com.lgu.common.map.model.findStatRoute.LocDataJSON;


public class ReqFindStatRouthSearchJSON {	
	
	private String searchOption;
	private String mrVersion;
	private String searchType;
	private boolean  usingDual =false;
	private boolean  directivity  =false;
	private boolean  rotate  =false;
	
	private LocDataJSON newStartloc;
	private LocDataJSON newEndloc;
	
	private List<ExtensionDataJSON> newExtensionList;

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getMrVersion() {
		return mrVersion;
	}

	public void setMrVersion(String mrVersion) {
		this.mrVersion = mrVersion;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public boolean isUsingDual() {
		return usingDual;
	}

	public void setUsingDual(boolean usingDual) {
		this.usingDual = usingDual;
	}

	public boolean isDirectivity() {
		return directivity;
	}

	public void setDirectivity(boolean directivity) {
		this.directivity = directivity;
	}

	public boolean isRotate() {
		return rotate;
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}

	public LocDataJSON getNewStartloc() {
		return newStartloc;
	}

	public void setNewStartloc(LocDataJSON newStartloc) {
		this.newStartloc = newStartloc;
	}

	public LocDataJSON getNewEndloc() {
		return newEndloc;
	}

	public void setNewEndloc(LocDataJSON newEndloc) {
		this.newEndloc = newEndloc;
	}

	public List<ExtensionDataJSON> getNewExtensionList() {
		return newExtensionList;
	}

	public void setNewExtensionList(List<ExtensionDataJSON> newExtensionList) {
		this.newExtensionList = newExtensionList;
	}
	
}

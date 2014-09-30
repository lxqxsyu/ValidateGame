package com.subao.validate.entity;

import java.util.List;

/**
 * 单个游戏或者应用的相关信息
 * @author Administrator
 *
 */
public class PackageNameInfo {
	private int id;
	private String matchesString;   //匹配字符串
	private List<String> packageNames;  //包名
	
	public PackageNameInfo(int id, String matchesString,
			List<String> packageNames) {
		super();
		this.id = id;
		this.matchesString = matchesString;
		this.packageNames = packageNames;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatchesString() {
		return matchesString;
	}
	public void setMatchesString(String matchesString) {
		this.matchesString = matchesString;
	}
	public List<String> getPackageNames() {
		return packageNames;
	}
	public void setPackageNames(List<String> packageNames) {
		this.packageNames = packageNames;
	}
}

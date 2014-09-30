package com.subao.validate.entity;

import java.util.List;

/**
 * ������Ϸ����Ӧ�õ������Ϣ
 * @author Administrator
 *
 */
public class PackageNameInfo {
	private int id;
	private String matchesString;   //ƥ���ַ���
	private List<String> packageNames;  //����
	
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

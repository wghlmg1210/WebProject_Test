package com.common.nmapScan.entity;

import java.io.Serializable;

/**
 * 主机信息实体类
 * 
 * @author Mr.Huang
 *
 */
public class HostInfo implements Serializable {

	private static final long serialVersionUID = 930280105035336968L;
	
	private String hostIP;		// 扫描的主机IP
	private PortInfo[] Ports;	// 开放的端口列表
	private String hostType;	// 主机类型
	private String scanTime;	// 扫描时间
	private String macAddress;	// Mac地址信息

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public PortInfo[] getPorts() {
		return Ports;
	}

	public void setPorts(PortInfo[] ports) {
		Ports = ports;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

}

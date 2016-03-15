package com.common.nmapScan.entity;

import java.io.Serializable;

/**
 * 端口信息类
 * 
 * @author Mr.Huang
 * 
 */
public class PortInfo implements Serializable {

	private static final long serialVersionUID = 1942698529272812477L;

	private int Port; // 端口
	private String transportPTC; // 传输协议
	private String State; // 端口状态
	private String Service; // 端口所处服务
	private String Version; // 服务版本

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public String getTransportPTC() {
		return transportPTC;
	}

	public void setTransportPTC(String transportPTC) {
		this.transportPTC = transportPTC;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getService() {
		return Service;
	}

	public void setService(String service) {
		Service = service;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

}

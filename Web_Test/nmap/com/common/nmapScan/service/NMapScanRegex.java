package com.common.nmapScan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.common.nmapScan.entity.HostInfo;
import com.common.nmapScan.entity.PortInfo;

/**
 * NMap扫描回写正则匹配解析类
 * 
 * @author Mr.Huang
 * 
 */
public class NMapScanRegex {

	/** 用于存储类中会使用到的正则的Map */
	private static Map<String, Pattern> patternMap = null;
	
	private static final String SIMPLEHOST = "SimpleHost";
	private static final String HOSTIP = "HostIP";
	private static final String MACADDR = "MacAddr";
	private static final String SIMPLEPORT = "SimplePort";
	private static final String SPACE = "Space";

	static {
		patternMap = new HashMap<String, Pattern>();
		/** 从整个文本内容中分离出单个主机信息 */
		patternMap.put(SIMPLEHOST, Pattern.compile(NMapScan.proes.getProperty(SIMPLEHOST)));

		/** 从主机信息中匹配到主机IP */
		patternMap.put(HOSTIP, Pattern.compile(NMapScan.proes.getProperty(HOSTIP)));

		/** 从主机信息中匹配到主机MAC */
		patternMap.put(MACADDR, Pattern.compile(NMapScan.proes.getProperty(MACADDR)));

		/** 从主机信息中分离出单个端口信息 */
		patternMap.put(SIMPLEPORT, Pattern.compile(NMapScan.proes.getProperty(SIMPLEPORT)));

		/** 解析端口信息 */
		patternMap.put(SPACE, Pattern.compile(NMapScan.proes.getProperty(SPACE)));
	}

	/**
	 * 将整个文本内容解析成主机列表
	 * 
	 * @param content
	 *            NMap回写数据
	 * @return
	 */
	public List<HostInfo> parseToHostList(String content) {
		List<HostInfo> hostInfoList = new ArrayList<HostInfo>();

		/** 获取单个主机信息 */
		String[] simpleHosts = patternMap.get(SIMPLEHOST).split(content);
		for (int i = 0; i < simpleHosts.length - 1; i++) {
			String simpleHost = simpleHosts[i];
			/** 解析主机信息 */
			hostInfoList.add(parseSimpleHost(simpleHost));
		}
		// 扫描耗时
		// System.out.println(simpleHosts[simpleHosts.length - 1]);
		return hostInfoList;
	}

	/**
	 * 解析单个主机信息
	 * 
	 * @param simpleHost
	 * @return
	 */
	public HostInfo parseSimpleHost(String simpleHost) {
		List<PortInfo> portInfoList = new ArrayList<PortInfo>();

		HostInfo hostInfo = new HostInfo();
		hostInfo.setHostIP(querySimple(patternMap.get(HOSTIP), simpleHost));
		hostInfo.setMacAddress(querySimple(patternMap.get(MACADDR), simpleHost));

		/** 获取单个端口信息 */
		String[] simplePorts = queryArray(patternMap.get(SIMPLEPORT), simpleHost);
		for (String simplePort : simplePorts) {
			/** 解析端口信息 */
			portInfoList.add(parseSimplePort(simplePort));
		}
		hostInfo.setPorts(portInfoList.toArray(new PortInfo[simplePorts.length]));
		return hostInfo;
	}

	/**
	 * 获取单个端口信息
	 * 
	 * @param pattern
	 * @param simpleHost
	 * @return
	 */
	public String[] queryArray(Pattern pattern, String simpleHost) {
		List<String> array = new ArrayList<String>();
		Matcher matcher = pattern.matcher(simpleHost);
		while (matcher.find()) {
			array.add(matcher.group());
		}
		return array.toArray(new String[array.size()]);
	}

	/**
	 * 解析单个端口信息
	 * 
	 * @param simplePort
	 *            单个端口信息的字符串
	 * @return
	 */
	public PortInfo parseSimplePort(String simplePort) {
		PortInfo portInfo = new PortInfo();
		String[] infoes = patternMap.get(SPACE).split(simplePort);
		for (int i = 0; i < 3; i++) {
			String[] p = infoes[0].split("/");
			portInfo.setPort(Integer.valueOf(p[0]));
			portInfo.setTransportPTC(p[1]);
			portInfo.setState(infoes[1]);
			if (infoes.length > 2)
				portInfo.setService(infoes[2]);
			if (infoes.length > 3)
				portInfo.setVersion(simplePort.substring(simplePort
						.indexOf(infoes[3])));
		}
		return portInfo;
	}

	/**
	 * 获取单个信息
	 * 
	 * @param pattern
	 * @param content
	 * @return
	 */
	public String querySimple(Pattern pattern, String content) {
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

}

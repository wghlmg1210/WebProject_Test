package com.common.nmapScan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.common.Constant;

/**
 * 扫描时用到的工具类
 * 
 * @author Mr.Huang
 * 
 */
public class ScanUtil {

	private static final String IPREGEX = "IPRegex"; 
	
	/** IP验证正则 */
	private static Pattern IPPattern = Pattern
			.compile(NMapScan.proes.getProperty(IPREGEX));

	public static List<String> splitIP(String startIP, String endIP) {
		List<String> IPSegments = new ArrayList<String>();
		int[] startIPArray = stringArrayToInt(startIP.split("\\."));
		int[] endIPArray = stringArrayToInt(endIP.split("\\."));
		int count = 0, i;
		for (i = startIPArray[2]; i < endIPArray[2]; i++) {
			if (++count == 1) {
				IPSegments.add(startIP + "-255");
			} else {
				IPSegments.add(startIPArray[0] + "." + startIPArray[1] + "."
						+ i + "." + "0-255");
			}
		}
		if (count == 0) {
			IPSegments.add(startIPArray[0] + "." + startIPArray[1] + "."
					+ startIPArray[2] + "." + startIPArray[3] + "-"
					+ endIPArray[3]);
		} else {
			IPSegments.add(startIPArray[0] + "." + startIPArray[1] + "."
					+ i + "." + "0-" + endIPArray[3]);
		}
		return IPSegments;
	}

	/**
	 * 检测IP格式是否正确
	 * 
	 * @param IPs
	 *            IP地址列表
	 * @return 返回IP格式错误的个数
	 */
	public static int checkIP(String[] IPs) {
		int errorCount = 0;
		for (String ip : IPs)
			if (!IPPattern.matcher(ip).matches())
				errorCount++;
		return errorCount;
	}

	/**
	 * 比较起始IP大小
	 * 
	 * @param startIP
	 *            开始IP
	 * @param endIP
	 *            结束IP
	 * @return 如果开始IP大于结束IP,返回-1;开始IP小于结束IP,返回1
	 */
	public static int compareIP(String startIP, String endIP) {
		double startIPNumber = converIPToNum(startIP);
		double endIPNumber = converIPToNum(endIP);
		return ((endIPNumber - startIPNumber) > 0 ? 1 : -1);
	}

	/**
	 * 计算起始IP的差值
	 * 
	 * @param startIP
	 * @param endIP
	 * @return 如果起始IP不在同一个B类IP段，返回-1
	 */
	public static int computeIPGap(String startIP, String endIP) {

		String BIP = startIP.substring(0,
				startIP.indexOf(".", startIP.indexOf(".") + 1));
		boolean bool = endIP.startsWith(BIP);
		if (!bool)
			return -1;
		double startIPNumber = converIPToNum(startIP);
		double endIPNumber = converIPToNum(endIP);
		return (int) (endIPNumber - startIPNumber);
	}

	/**
	 * 验证端口列表中是否存在无效端口
	 * 
	 * @param ports
	 *            端口列表
	 * @return
	 */
	public static boolean hasInvalidPort(int[] ports) {
		for (int port : ports)
			if (port < Constant.MINPORTNUM || port > Constant.MAXPORTNUM)
				return true;
		return false;
	}

	/**
	 * 端口列表是否为空
	 * 
	 * @param ports
	 *            端口列表
	 * @return
	 */
	public static boolean isNullPorts(int[] ports) {
		return ports == null || ports.length == 0;
	}

	/**
	 * 端口列表中是否有重复端口信息
	 * 
	 * @param ports
	 *            端口列表
	 * @return
	 */
	public static boolean hasRepeatPorts(int[] ports) {
		for (int i = 0; i < ports.length; i++) {
			for (int j = i + 1; j < ports.length; j++) {
				if (ports[i] == ports[j])
					return true;
			}
		}
		return false;
	}

	/**
	 * 将IP地址转换成数值
	 * 
	 * @param ip
	 * @return
	 */
	private static double converIPToNum(String ip) {
		String[] numArray = ip.split("\\.");
		return Integer.valueOf(numArray[0]) * Math.pow(255, 3)
				+ Integer.valueOf(numArray[1]) * Math.pow(255, 2)
				+ Integer.valueOf(numArray[2]) * Math.pow(255, 1)
				+ Integer.valueOf(numArray[3]) * Math.pow(255, 0);
	}

	private static int[] stringArrayToInt(String[] arrs) {
		int[] ints = new int[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}

}

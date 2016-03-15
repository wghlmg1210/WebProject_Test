package com.common;

/**
 * 常量类
 * 
 * @author Mr.Huang
 * 
 */
public class Constant {

	public static class ScanResultType {
		/** 扫描成功 */
		public static final int SUCCESS = 200;
		/** IP信息格式不正确 */
		public static final int IPPATTERNERROR = 201;
		/** IP起始大小不正确 */
		public static final int IPSTARTENDERROR = 202;
		/** IP不在同一个B类IP段 */
		public static final int IPISNUTBTYPE = 203;
		/** IP段跨度过大 */
		public static final int IPGAPTOOMUCH = 204;
		/** 无端口信息 */
		public static final int PORTSISNULL = 205;
		/** 端口列表中存在无效端口 */
		public static final int PORTSISINVALID = 206;
		/** 端口列表中存在重复端口 */
		public static final int PORTSHASREPEAT = 207;
	}
	
	/** 最大端口号 */
	public static final int MAXPORTNUM = 65535;
	
	/** 最小端口号 */
	public static final int MINPORTNUM = 0;
	
	/** 最大可解析IP跨度 */
	public static final int MAXIPGAP = 65535;
	
}

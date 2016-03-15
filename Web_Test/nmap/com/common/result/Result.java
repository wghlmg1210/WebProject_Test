package com.common.result;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * 结果信息包装类 	不同任务调用该类进行回馈时请书写任务的回馈编码及摘要清单，用于对接
 * 	 NMap端口扫描任务任务清单 -- NMapScanResult.properties
 * 
 * @author Mr.Huang
 * 
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1281831424222664680L;

	/** 读取结果信息集配置文件 */
	private Properties proes;
	
	public Result() {
	}

	/** 将结果信息列表载入proes中 
	 * @throws IOException */
	public Result(String propPath) throws IOException {
		proes = new Properties();
		proes.load(Result.class.getClassLoader().getResourceAsStream(propPath));
	}
	
	public Result(Properties proes) {
		this.proes = proes;
	}

	/** 回馈信息编码 */
	private int rstCode;

	/** 回馈信息摘要 */
	private String rstMsg;

	/** 回馈信息数据列表 */
	private List<T> rstData;

	public int getRstCode() {
		return rstCode;
	}

	public void setRstCode(int rstCode) {
		this.rstCode = rstCode;
	}

	public String getRstMsg() {
		return rstMsg;
	}

	public void setRstMsg(String rstMsg) {
		this.rstMsg = rstMsg;
	}

	public List<T> getRstData() {
		return rstData;
	}

	public Result<T> setRstData(List<T> rstData) {
		this.rstData = rstData;
		return this;
	}
	
	/** 根据rstCode，从proes中获取并设置rstMsg */
	public Result<T> setRstInfo(int rstCode) {
		this.rstCode = rstCode;
		String msg = proes.getProperty(new Integer(rstCode).toString());
		if(msg == null || "".equals(msg)) {
			throw new IllegalArgumentException("无rstCode = " + rstCode + " 对应的rstMsg信息");
		}
		this.rstMsg = msg;
		return this;
	}

}

package com.common.nmapScan.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.common.nmapScan.entity.HostInfo;
import com.common.util.StreamUtil;

/**
 * NMap扫描任务类
 * 
 * @author Mr.Huang
 * 
 */
public class NMapScanTask extends Thread {

	private static final String EXEPATH = "exePath";
	private static final String COMMAND = "command";
	
	private List<HostInfo> rstData;

	private final String IPSegment;

	private final int[] ports;

	/** 描述任务是否已经结束 */
	private volatile Boolean flag;

	NMapScanTask(String IPSegment, int[] ports) {
		this.IPSegment = IPSegment;
		this.ports = ports;
	}

	@Override
	public void run() {
		flag = false;

		/** 开启NMap进程开始扫描 */
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(NMapScan.proes.getProperty(EXEPATH) + " "
					+ NMapScan.proes.getProperty(COMMAND) + " " + Arrays.toString(this.ports)
					+ " " + this.IPSegment);
			String content = StreamUtil.readContent(process.getInputStream());
			rstData = new NMapScanRegex().parseToHostList(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

		flag = true;
	}

	public List<HostInfo> getRstData() {
		return rstData;
	}

	public Boolean getFlag() {
		return flag;
	}

}

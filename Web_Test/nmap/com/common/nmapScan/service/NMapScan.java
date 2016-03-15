package com.common.nmapScan.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.common.Constant;
import com.common.nmapScan.entity.HostInfo;
import com.common.result.Result;

/**
 * NMap端口扫描信息类
 * 
 * @author Mr.Huang
 * 
 */
public class NMapScan {
	
	/** 维护所有正在运行的任务引用 */
	private static Map<String, NMapScan> nmapTaskId = new ConcurrentHashMap<String, NMapScan>();
	
	protected static Properties proes;
	
	/** NMap配置文件路径 */
	public static final String NMAPPROPERTIES = "nmap.properties";
	public static final String DELAY = "delay";
	public static final String PERIOD = "period";
	public static final String THREADNUM = "ThreadNum";
	
	static {
		proes = new Properties();
		try {
			proes.load(NMapScan.class.getClassLoader().getResourceAsStream(NMAPPROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NMapScan(String taskId) {
		this.taskId = taskId;
		NMapScan.nmapTaskId.put(taskId, this);
	}

	private String taskId;
	private int taskSum;
	private float progress;
	
	/** 所有扫描结果信息列表 */
	private List<HostInfo> dataList = new ArrayList<HostInfo>();
	
	private ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(proes.getProperty(THREADNUM)));

	/** 当前扫描任务的子任务引用维护Map */
	private Map<String, NMapScanTask> scanTaskMap = new ConcurrentHashMap<String, NMapScanTask>();

	/**
	 * 扫描任务主入口
	 * 
	 * @param startIP	开始扫描IP
	 * @param endIP		结束扫描IP
	 * @param ports		扫描端口列表
	 * @return			扫描结果
	 */
	public synchronized Result<?> scanAndParse(String startIP, String endIP,
			int[] ports) {
		/** 检测输入参数的合理性 */
		Result<?> result = checkInfo(startIP, endIP, ports);
		if(result.getRstCode() != 0) return result;
		
		/** 启动多线程扫描任务 */
		List<String> IPSegments = ScanUtil.splitIP(startIP, endIP);
		for (String IPSegment : IPSegments) {
			NMapScanTask nmapScanTask = new NMapScanTask(IPSegment, ports);
			scanTaskMap.put(taskId + ++taskSum, nmapScanTask);
			executor.execute(nmapScanTask);
		}
		return result.setRstInfo(Constant.ScanResultType.SUCCESS);
	}
	
	/**
	 * 开启定时器进行扫描结束收集
	 * 
	 * @return
	 */
	public void startCollectResult() {
		/** 启动定时器，回收扫描结果，并清理多线程资源 */
		Timer timer = new Timer();
		Long delay = Long.valueOf(proes.getProperty(DELAY));
		Long period = Long.valueOf(proes.getProperty(PERIOD));
		timer.schedule(new NMapResultTask(timer), delay, period);
	}

	/**
	 * 检测输入参数的合理性
	 * 
	 * @param startIP	开始IP
	 * @param endIP		结束IP
	 * @param ports		扫描端口列表
	 * @return
	 */
	private Result<HostInfo> checkInfo(String startIP, String endIP, int[] ports) {
		Result<HostInfo> result = new Result<HostInfo>(NMapScan.proes);
		/** 检测IP格式 */
		if (ScanUtil.checkIP(new String[] { startIP, endIP }) != 0) {
			return result.setRstInfo(Constant.ScanResultType.IPPATTERNERROR);
		}
		/** 检测IP起始大小 */
		if (ScanUtil.compareIP(startIP, endIP) == -1) {
			return result.setRstInfo(Constant.ScanResultType.IPSTARTENDERROR);
		}
		/** 检测IP跨度是否过大 */
		int gap = ScanUtil.computeIPGap(startIP, endIP);
		if (gap == -1) {
			return result.setRstInfo(Constant.ScanResultType.IPISNUTBTYPE);
		}
		if (gap > Constant.MAXIPGAP) {
			return result.setRstInfo(Constant.ScanResultType.IPGAPTOOMUCH);
		}
		/** 检测ports是否为空 */
		if (ScanUtil.isNullPorts(ports)) {
			return result.setRstInfo(Constant.ScanResultType.PORTSISNULL);
		}
		/** 检测ports中是否有无效端口 */
		if (ScanUtil.hasInvalidPort(ports)) {
			return result.setRstInfo(Constant.ScanResultType.PORTSISINVALID);
		}
		/** 检测ports中是否有重复端口 */
		if (ScanUtil.hasRepeatPorts(ports)) {
			return result.setRstInfo(Constant.ScanResultType.PORTSHASREPEAT);
		}
		return result;
	}

	/**
	 * 将部分扫描结果添加到dataList列表中
	 * 
	 * @param hostInfoes
	 *            部分扫描结果
	 */
	private synchronized void addHostInfo(List<HostInfo> hostInfoes) {
		this.dataList.addAll(hostInfoes);
	}
	
	/**
	 * 获取当前任务的扫描进度
	 * 
	 * @return
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * 定时扫描任务 用于检测扫描任务是否已经完成，若完成则收集扫描结果，并清理线程
	 * 
	 * @author Mr.Huang
	 * 
	 */
	class NMapResultTask extends TimerTask {

		/** 定时器引用 */
		private Timer timer;

		NMapResultTask(Timer timer) {
			this.timer = timer;
		}

		@Override
		public void run() {
			progress = 0.0f;
			/** 记录正在扫描的任务的数量 */
			int scaningTaskNum = 0, waitingNum = 0;
			/** 记录已经完成扫描的任务的key */
			List<String> keyList = new ArrayList<String>();

			for (String key : scanTaskMap.keySet()) {
				NMapScanTask nmapScanTask = scanTaskMap.get(key);
				/** 任务正在排队中，为执行扫描 */
				if(nmapScanTask.getFlag() != null) {
					if (nmapScanTask.getFlag()) {
						keyList.add(key);
					} else {
						scaningTaskNum++;
					}
				} else {
					waitingNum++;
				}
			}

			/** 扫描进度 = 已完成/总数 + 正在进行/（总数*2） */
			progress = (float) scaningTaskNum / (taskSum * 2)
					+ (float) (taskSum - scaningTaskNum - waitingNum) / taskSum;
			
			/** 收集扫描结果，并清理线程 */
			collectResultAndClearThread(keyList);
			
			/** 扫描任务全部结束，清理定时器 */
			if (scaningTaskNum == 0) {
				timer.cancel();
				executor.shutdown();
				timer = null;
				executor = null;
				NMapScan.nmapTaskId.remove(taskId);
			}
		}

		/**
		 * 收集扫描结果，并清理线程
		 * 
		 * @param keyList
		 *            需要处理的扫描线程key集合
		 */
		private void collectResultAndClearThread(List<String> keyList) {
			for (String key : keyList) {
				NMapScanTask nmapScanTask = scanTaskMap.get(key);
				System.out.println(nmapScanTask.getRstData().size());
				addHostInfo(nmapScanTask.getRstData());
				scanTaskMap.remove(key);
			}
		}

	}
	
}

package com.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 不重复序列生成器
 * 
 * @author Mr.Huang
 * 
 */
public class Sequence {

	/** 保存当前最新的数据 */
	private volatile static String currentSeqVal = null;

	/** 序列重复椎（当序列出现重复时，自增该属性，并拼接到序列中） */
	private volatile static int count = 0;

	/** 该序列最大重复因子 */
	private static final int MAXCOUNT = 4;

	/** 时间格式化对象 */
	private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	/**
	 * 一个同步函数 用于获取时间戳
	 * 
	 * @return
	 */
	public synchronized static String getSeqVal() {
		String time = SIMPLEDATEFORMAT.format(new Date());
		if (time.equals(currentSeqVal)) {
			count++;
		} else {
			refreshCount();
		}
		currentSeqVal = time;
		return assemble(time, count);
	}

	/**
	 * 刷新序列重复椎
	 * 
	 * @return
	 */
	private static void refreshCount() {
		count = 0;
	}

	/**
	 * 将时间戳与序列重复椎拼接
	 * 
	 * @param time
	 *            时间戳
	 * @param count
	 *            序列重复椎
	 * @return
	 */
	private static String assemble(String time, int count) {
		int fillLength = MAXCOUNT - new Integer(count).toString().length();
		String fileStr = "";
		for (int i = 0; i < fillLength; i++) {
			fileStr += "0";
		}
		return time + fileStr + count;
	}

}

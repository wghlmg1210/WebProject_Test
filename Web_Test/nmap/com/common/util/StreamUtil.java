package com.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流读写工具类
 * 
 * @author Mr.Huang
 * 
 */
public class StreamUtil {

	public static String readContent(InputStream input) throws IOException {
		return readContent(input, "GBK");
	}

	/**
	 * 将input流中的内容全部读取，该函数会一次性将所有的内容读取到内存中，所以要是内容比较多，请不要使用这个函数
	 * 
	 * @param input
	 *            InputStream
	 * @param encoding
	 *            内容编码
	 * @return
	 * @throws IOException
	 */
	public static String readContent(InputStream input, String encoding)
			throws IOException {
		System.out.println("-------------------");
		BufferedReader in = new BufferedReader(new InputStreamReader(input,
				encoding));
		StringBuffer strb = new StringBuffer();
		String str = null;
		while ((str = in.readLine()) != null) {
			strb.append(str.toLowerCase()).append("\r\n");
		}
		return strb.toString();
	}

}

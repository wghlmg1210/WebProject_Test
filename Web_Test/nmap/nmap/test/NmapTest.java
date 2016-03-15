package nmap.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NmapTest {
	public static void main(String[] args) throws Exception {
		Runtime rt = Runtime.getRuntime();
		// Process p =
		// rt.exec("\"D:\\Program Files (x86)\\Nmap\\nmap.exe\" -p8009,1433,8080,49152,49153,49154,49155,49156,3389 -Pn 115.236.12.164");
		// nmap -sV -T4 -p 8080,80 192.168.4.0-255
		// Process p =
		// rt.exec("\"D:\\Program Files (x86)\\Nmap\\nmap.exe\" -p18080 -Pn 120.193.10.212");
		long start = System.currentTimeMillis();
		// -sV -T4 -p 8080,80 192.168.
		// Process p =
		// rt.exec("\"D:\\Program Files (x86)\\Nmap\\nmap.exe\" -T4 -A -v 192.168.1.1-255");
		Process p = rt
				.exec("D:\\Program Files (x86)\\Nmap\\nmap -sV -T4  -p 8080,80,8443,8888,18080 192.168.1.1-255");

		BufferedReader in = null;
		StringBuilder strb = new StringBuilder();
		String str = "";
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream(),
					"GBK"));
			while ((str = in.readLine()) != null) {
				str = str.toLowerCase();
				strb.append(str);
				System.out.println(str);
			}
			System.out.println("=========");
			System.out.println("消耗时间："
					+ ((System.currentTimeMillis() - start) / 1000) + "秒");
			System.out.println(strb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}

	}
}

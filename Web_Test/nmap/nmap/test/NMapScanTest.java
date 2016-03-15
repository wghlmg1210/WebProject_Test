package nmap.test;

import com.common.nmapScan.service.NMapScan;
import com.common.result.Result;

public class NMapScanTest {

	public static void main(String[] args) {
		NMapScan nmapScan = new NMapScan("webDiscover_100");
		int[] ports = new int[] { 8080, 80, 8443, 8888, 18080 };
		long s = System.currentTimeMillis();
		Result<?> result = nmapScan.scanAndParse("192.168.0.0",
				"192.168.255.255", ports);
		System.out.println(result);
		nmapScan.startCollectResult();
		while (nmapScan.getProgress() != 1) {
			try {
				Thread.sleep(10000);
				System.out.println(nmapScan.getProgress());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("扫描时间：" + (System.currentTimeMillis() - s));
	}

}

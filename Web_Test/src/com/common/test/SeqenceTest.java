package com.common.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.util.Sequence;

public class SeqenceTest {

	private static List<String> seq = Collections
			.synchronizedList(new ArrayList<String>());

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new ThreadTest().start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getRepeat());

	}

	static class ThreadTest extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				String s = Sequence.getSeqVal();
				SeqenceTest.seq.add(s);
			}
			System.out.println("end");
		}

	}

	public static Map<String, Integer> getRepeat() {
		System.out.println("start");
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (int i = 0; i < SeqenceTest.seq.size(); i++) {
			String val1 = SeqenceTest.seq.get(i);
			int count = 0;
			for (int j = i + 1; j < SeqenceTest.seq.size(); j++) {
				String val2 = SeqenceTest.seq.get(j);
				if (val2.equals(val1)) {
					count++;
				}
			}
			if (count != 0) {
				result.put(val1, count);
			}
		}
		return result;
	}

}

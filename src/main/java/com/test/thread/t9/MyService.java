package com.test.thread.t9;

public class MyService {

	public MyOneList addServiceMethod(MyOneList list, String data) {

		try {
			if (list.getSize() < 1) {
				System.out.println(Thread.currentThread().getName() + ",,addServiceMethod,begin if" + list.toString());
				Thread.sleep(2000);// 模拟从远程花费2s取回数据
				list.add(data);
				System.out.println(Thread.currentThread().getName() + ",addServiceMethod end");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
}

package com.yang.other;

import java.io.IOException;

public class RunTimeTest {
	public static void main(String[] args) {
		// 本类没有构造方法，只能通过getRuntime创建对象
		Runtime rt = Runtime.getRuntime();
		try {
			// 打开某文件 还能用此程序打开某文件
			Process p = rt.exec("D:\\Tencent\\TIM\\Bin\\QQScLauncher.exe");
			// 杀死上进程
			p.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.yang.other.annotation.d2;

import org.junit.Test;

@Decription(id = "123")
public class ActiveExtend {
	@Test
	@Decription(id = "123", decription = "方法上的注解")
	public void test1() {

	}
}

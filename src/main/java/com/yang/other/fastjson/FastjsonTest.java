package com.yang.other.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastjsonTest {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("first_name", "孙阳阳");
		json.put("date", "1999-09-09");
		JSONObject entyr2 = new JSONObject();
		entyr2.put("date", "1990-09-09");
		json.put("entry2", entyr2);
		Entry parseObject = JSONObject.parseObject(JSON.toJSONString(json), Entry.class);
		System.out.println(parseObject);
	}
}

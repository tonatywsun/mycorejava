package com.yang.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Entry {
	@JSONField(name = "first_name")
	private String firstName;

	@JSONField(format = "yyyy-MM-dd")
	private Date date;

	private Entry2 entry2;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Entry2 getEntry2() {
		return entry2;
	}

	public void setEntry2(Entry2 entry2) {
		this.entry2 = entry2;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

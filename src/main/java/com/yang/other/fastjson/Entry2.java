package com.yang.other.fastjson;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Entry2 {
	@JSONField(format = "yyyy-MM-dd")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

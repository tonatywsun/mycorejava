package com.yang.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

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

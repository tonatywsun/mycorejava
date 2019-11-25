package com.yang.core.string;

public class Entry {
	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	/**
	 * 此方法调用是会无限调用toString发生error
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Entry [i=" + this + "]";
	}

	public static void main(String[] args) {
		System.out.println(new Entry());
	}
}

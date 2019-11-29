package com.yang.lottery;

public class Reward {

	/**
	 * 奖品编号
	 */
	private int id;

	/**
	 * 奖品总量
	 */
	private int quantity;

	/**
	 * 奖品库存
	 */
	private int inventory;

	/**
	 * 奖品名称
	 */
	private String name;

	/**
	 * 中奖概率
	 */
	private float probability;

	public Reward() {
		super();
	}

	public Reward(int id, int quantity, int inventory, String name, float probability) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.inventory = inventory;
		this.name = name;
		this.probability = probability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

	@Override
	public String toString() {
		return "Reward [id=" + id + ", quantity=" + quantity + ", inventory=" + inventory + ", name=" + name + ", probability=" + probability + "]";
	}

}

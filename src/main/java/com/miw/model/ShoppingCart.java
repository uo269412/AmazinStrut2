package com.miw.model;

import java.util.HashMap;

public class ShoppingCart {

	private HashMap<String, Integer> list = new HashMap<String, Integer>();
	private double cost = 0.0;

	public void add(String element) {
		if (list.get(element) != null) {
			list.replace(element, list.get(element) + 1);
		} else {
			list.put(element, 1);
		}
	}

	public void addCost(double quantity) {
		this.cost += quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public HashMap<String, Integer> getList() {
		return list;
	}

}

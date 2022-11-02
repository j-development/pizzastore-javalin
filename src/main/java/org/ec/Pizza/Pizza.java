package org.ec.Pizza;

public class Pizza {
	private int id, price, pizzagroup;
	private String name;

	public Pizza(int id, String name, int price, int pizzagroup) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.pizzagroup = pizzagroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPizzagroup() {
		return pizzagroup;
	}

	public void setPizzagroup(int pizzagroup) {
		this.pizzagroup = pizzagroup;
	}
}

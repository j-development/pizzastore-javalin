package org.ec.Pizza;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class PizzaRepositoryTest {

	List<Pizza>	pizzas;

	@BeforeEach
	void setUp() {
		Pizza pizza1 = new Pizza(1, "VESUVIO", 100, 1);
		Pizza pizza2 = new Pizza(1, "CAPRICIOSSA", 100, 1);
		Pizza pizza3 = new Pizza(1, "MARGARITA", 100, 1);

		pizzas = List.of(pizza1, pizza2, pizza3);
	}



}

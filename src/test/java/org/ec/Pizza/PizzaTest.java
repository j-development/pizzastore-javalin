package org.ec.Pizza;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PizzaTest {
	@Test
	void succesfullyCreatePizzaAndTestConstructor() {
		Pizza pizza1 = new Pizza(1, "VESUVIO", 100, 1);

		assertEquals(1, pizza1.getId());
		assertEquals("VESUVIO", pizza1.getName());
		assertEquals(100, pizza1.getPrice());
		assertEquals(1, pizza1.getPizzagroup());
	}
	@Test
	void unsuccessfullyCreatePizza() {
		Pizza pizza1 = new Pizza(1, "VESUVIO", 100, 1);

		assertNotEquals(2, pizza1.getId());
		assertNotEquals("Margarita", pizza1.getName());
		assertNotEquals(90, pizza1.getPrice());
		assertNotEquals(2, pizza1.getPizzagroup());
	}
}

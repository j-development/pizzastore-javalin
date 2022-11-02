package org.ec.Pizza;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaTest {
	@Test
	void succesfullyCreatePizzaAndTestConstructor() {
		Pizza pizza1 = new Pizza(1, "VESUVIO", 100, 1);

		assertEquals(2, pizza1.getId());
	}
}

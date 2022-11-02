package org.ec.Pizza;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

	@Test
	void testGetListOfPizzas() {
		// given
		Mockito.when(classUnderTest.findAll()).thenReturn(pizzas);
		// when
		List<Pizza> result = classUnderTest.findAll();
		// then
		Assertions.assertEquals(result, pizzas);
	}


}

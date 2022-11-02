package org.ec.Pizza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PizzaControllerTest {

	@Mock
	private PizzaService pizzaService;
	private PizzaController classUnderTest;

	@BeforeEach
	void setUp() {
		classUnderTest = new PizzaController(pizzaService);
	}

	@Test
	void GET_ToReceiveAllPizzasSuccessfully() {
		//given
		List<Pizza> pizzas = List.of(new Pizza(1, "VESUVIO", 100, 1), new Pizza(2, "CAPRICIOSSA", 100, 1), new Pizza(3, "MARGARITA", 100, 1));
		when(pizzaService.getAllPizzas()).thenReturn(pizzas);
		//when
		classUnderTest.fetchAllPizzas();


	}
}

package org.ec.Pizza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

	}
}

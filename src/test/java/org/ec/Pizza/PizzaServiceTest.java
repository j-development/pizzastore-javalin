package org.ec.Pizza;

import org.ec.Pizza.exceptions.PizzaNameAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {

	@Mock
	PizzaRepository pizzaRepository;
	PizzaService classUnderTest;

	@BeforeEach
	void setUp() {
		classUnderTest = new PizzaService(pizzaRepository);
	}

	@Test
	void throwErrorWhenPizzaNameAlreadyTaken() throws PizzaNameAlreadyExists {
		// given
		String pizzaName = "VESUVIO";
		Pizza pizza1 = new Pizza(1, pizzaName, 100, 1);
		// when
		Mockito.when(pizzaRepository.pizzaNameExists(pizzaName)).thenReturn(true);
		// then
		assertThatThrownBy(() -> classUnderTest.addPizza(pizza1))
				.isInstanceOf(PizzaNameAlreadyExists.class)
				.hasMessageContaining("Pizza name "+pizzaName+" already taken");
	}
}

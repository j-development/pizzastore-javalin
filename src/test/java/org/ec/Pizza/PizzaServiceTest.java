package org.ec.Pizza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
	void throwErrorWhenPizzaNameAlreadyTaken() {
		// given
		Pizza pizza1 = new Pizza(1, "VESUVIO", 100, 1);
		Pizza pizza2 = new Pizza(1, "VESUVIO", 100, 1);
		// when
		classUnderTest.add(pizza1);
		classUnderTest.add(pizza2);
		// then
		assertThatThrownBy(() -> classUnderTest.add(pizza2))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Pizza name already taken");
	}
}

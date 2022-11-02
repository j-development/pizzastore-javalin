package org.ec.Pizza;

import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PizzaControllerTest {

	private final Context ctx = mock(Context.class);

	@Mock
	private PizzaService pizzaService;
	private PizzaController classUnderTest;

	@BeforeEach
	void setUp() {
		classUnderTest = new PizzaController(pizzaService);
	}

	@Test
	void GET_ToReceiveAllPizzasSuccessfully() throws Exception {
		//given
		List<Pizza> pizzas = List.of(new Pizza(1, "VESUVIO", 100, 1), new Pizza(2, "CAPRICIOSSA", 100, 1), new Pizza(3, "MARGARITA", 100, 1));
		when(pizzaService.getAllPizzas()).thenReturn(pizzas);
		//when
		classUnderTest.fetchAllPizzas().handle(ctx);
		//then
		verify(ctx).json(pizzas);
	}

	@Test
	void POST_ToCreateNewPizzaSuccessfully() throws Exception {
		//given
		var pizzaId = 1;
		Pizza vesuvio1 = new Pizza(pizzaId, "VESUVIO", 100, 1);
		when(ctx.json(vesuvio1)).thenReturn(ctx);
		//when
		classUnderTest.createPizza().handle(ctx);
		//then
		verify(ctx).status(201);
	}

	@Test
	@Disabled
	void PUT_ToUpdatePizzaSuccessfully() throws Exception {
		//given
		var pizzaId = 1;
		Pizza vesuvio1 = new Pizza(1, "VESUVIO", 100, 1);
		Pizza vesuvio2 = new Pizza(1, "VESUVIO", 100, 1);
		when(pizzaService.findPizzaById(pizzaId)).thenReturn(vesuvio1);
		//when
//		classUnderTest.createPizza(vesuvio2).handle(ctx);
	}
}

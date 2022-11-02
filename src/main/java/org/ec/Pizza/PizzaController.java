package org.ec.Pizza;
import io.javalin.http.Handler;

public class PizzaController {
	private final PizzaService pizzaService;
	public PizzaController(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}

	public Handler fetchAllPizzas() {
		return ctx -> {
			var pizzas = pizzaService.getAllPizzas();
			ctx.json(pizzas);
		};
	}
}

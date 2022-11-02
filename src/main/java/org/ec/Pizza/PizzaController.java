package org.ec.Pizza;

import com.google.gson.Gson;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.Objects;


public class PizzaController {
	private final PizzaService pizzaService;
	private final Gson gson = new Gson();

	public PizzaController(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}

	public Handler fetchAllPizzas() {
		return ctx -> {
			var pizzas = pizzaService.getAllPizzas();
			ctx.json(pizzas);
		};
	}

	public Handler createPizza() {
		return ctx -> {
			var reqObj = (HashMap<String, Object>) gson.fromJson(ctx.body(), HashMap.class);
			if(reqObj == null){
				throw new BadRequestResponse("Invalid request body");
			}
			var pizzaName = (String) reqObj.get("name");
			boolean pizzaAlreadyExist = pizzaService.pizzaNameExists(pizzaName);
			if (pizzaAlreadyExist) {
				throw new BadRequestResponse();
			}
			Double id = (Double) reqObj.get("id");
			Double price = (Double) reqObj.get("price");
			Double pizzagroup = (Double) reqObj.get("pizzagroup");
			var pizza = new Pizza(
					id.intValue(),
					pizzaName,
					price.intValue(),
					pizzagroup.intValue()
			);
			var pizzaFromDb = pizzaService.addPizza(pizza);
			ctx.status(201).json(pizzaFromDb);

		};
	}


}

package org.ec.Pizza;

import io.javalin.http.BadRequestResponse;
import org.ec.Pizza.exceptions.PizzaNameAlreadyExists;

public class PizzaService {

	private final PizzaRepository pizzaRepository;

	public PizzaService(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	public void addPizza(Pizza pizza) throws PizzaNameAlreadyExists {
		var pizzaName = pizza.getName();
		var res = pizzaRepository.pizzaNameExists(pizzaName);
		if(res){
			throw new PizzaNameAlreadyExists("Pizza name " + pizza.getName() + " already taken");
		}
	}

	public void updatePizza(Pizza pizza) {
		var pizzaId = pizza.getId();
		var res = pizzaRepository.findById(pizzaId);

		Pizza pizzaDb = res.orElseThrow(() -> new BadRequestResponse("Bad Id Received"));
		pizzaDb.setName(pizza.getName());
		pizzaDb.setPrice(pizza.getPrice());
		pizzaDb.setPizzagroup(pizza.getPizzagroup());

		pizzaRepository.save(pizzaDb);
	}

	public Pizza findPizzaById(int id) {
		var res = pizzaRepository.findById(id);
		return res.orElseThrow(() -> new BadRequestResponse("Bad Id Received"));
	}
}

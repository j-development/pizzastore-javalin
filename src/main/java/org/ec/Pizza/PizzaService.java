package org.ec.Pizza;

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
}

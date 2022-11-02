package org.ec.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository {
	List<Pizza> findAll();
	void add(Pizza pizza);
	void deleteById(int id);

	Optional<Pizza> findById(int id);

	Pizza save(Pizza pizza);

	Boolean pizzaNameExists(String pizzaName);

}

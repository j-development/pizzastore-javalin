package org.ec;

import io.javalin.Javalin;
import org.ec.Pizza.Pizza;
import org.ec.Pizza.PizzaController;
import org.ec.Pizza.PizzaRepository;
import org.ec.Pizza.PizzaService;

import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		PizzaController pizzaController = new PizzaController(new PizzaService(new PizzaRepository() {
			List<Pizza> pizzas = List.of(new Pizza(1, "VESUVIO", 100, 1), new Pizza(2, "MARGARITA", 90, 2));
			@Override
			public List<Pizza> findAll() {
				return pizzas;
			}

			@Override
			public void add(Pizza pizza) {

			}

			@Override
			public void deleteById(int id) {

			}

			@Override
			public Optional<Pizza> findById(int id) {
				return Optional.empty();
			}

			@Override
			public Pizza save(Pizza pizza) {
				return null;
			}

			@Override
			public Boolean pizzaNameExists(String pizzaName) {
				return null;
			}
		}));


		Javalin App = Javalin.create()
				.get("/", pizzaController.fetchAllPizzas())
				.post("/", ctx -> ctx.result("Skapa en ny pizza"))
				.delete("/{id}", ctx -> ctx.result("Radera en specifik pizza baserat på id: " + ctx.pathParam("id")))
				.get("/groups", ctx -> ctx.result("Hämta alla pizzagrupper"))
				.put("/", ctx -> ctx.result("Uppdatera en specifik pizza"))
				.post("/order", ctx -> ctx.result("Skapa en ny pizzaorder"))
				.start(5000);
	}
}
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
				// Hardcoded for testing purposes
				Pizza vesuvio = new Pizza(1, "VESUVIO", 100, 1);
				return Optional.of(vesuvio);
			}

			@Override
			public Pizza save(Pizza pizza) {
				return null;
			}

			@Override
			public Boolean pizzaNameExists(String pizzaName) {
				return false; // Hardcoded always false, for testing purposes
			}
		}));


		createApp(pizzaController).start(5000);
	}

	public static Javalin createApp(PizzaController pizzaController){
		var app = Javalin.create()
				.get("/pizza", pizzaController.fetchAllPizzas())
				.post("/pizza", pizzaController.createPizza())
				.delete("/{id}", ctx -> ctx.result("Radera en specifik pizza baserat på id: " + ctx.pathParam("id")))
				.get("/groups", ctx -> ctx.result("Hämta alla pizzagrupper"))
				.put("/", ctx -> ctx.result("Uppdatera en specifik pizza"))
				.post("/order", ctx -> ctx.result("Skapa en ny pizzaorder"));

			return app;
	}
}
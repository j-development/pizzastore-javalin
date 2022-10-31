package org.ec;

import io.javalin.Javalin;

public class Main {
	public static void main(String[] args) {
		Javalin App = Javalin.create()
				.get("/", ctx -> ctx.result("Hämta en lista på alla pizzor"))
				.post("/", ctx -> ctx.result("Skapa en ny pizza"))
				.delete("/{id}", ctx -> ctx.result("Radera en specifik pizza baserat på id: " + ctx.pathParam("id")))
				.get("/groups", ctx -> ctx.result("Hämta alla pizzagrupper"))
				.put("/", ctx -> ctx.result("Uppdatera en specifik pizza"))
				.post("/order", ctx -> ctx.result("Skapa en ny pizzaorder"))
				.start(5000);
	}
}
package org.ec.Pizza;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.ec.Main;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MainTest {

    private final Gson gson = new Gson();

    HashMap<Integer, Pizza> testStorage = new HashMap<Integer, Pizza>();
    HashMap<Integer, Pizza> comparisonStorage = new HashMap<Integer, Pizza>();
    PizzaRepository testRepo = new InMemoryPizzaRepository(testStorage);
    PizzaService testService = new PizzaService(testRepo);
    PizzaController testController = new PizzaController(testService);

    @BeforeEach
    void resetStorage() {
        testStorage.clear();
        testStorage.put(1, new Pizza(1, "VESUVIO", 100, 1));
        testStorage.put(2, new Pizza(2, "CAPRICIOSSA", 100, 1));
        testStorage.put(3, new Pizza(3, "MARGARITA", 100, 1));

        comparisonStorage.clear();
        comparisonStorage.put(1, new Pizza(1, "VESUVIO", 100, 1));
        comparisonStorage.put(2, new Pizza(2, "CAPRICIOSSA", 100, 1));
        comparisonStorage.put(3, new Pizza(3, "MARGARITA", 100, 1));
    }

    Javalin testApp = Main.createApp(testController);

    @Test
    @DisplayName("Get all pizzas")
    void test_get_all(){
        JavalinTest.test(testApp, (server, client) -> {
            var response = client.get("/pizza");

            ArrayList<Pizza> listFromDb = new ArrayList<>(comparisonStorage.values());
            var jsonFromDb = gson.toJson(listFromDb);

            Assertions.assertEquals(200, response.code());
            Assertions.assertEquals(jsonFromDb, response.body().string());
        });
    }

    @Test
    @DisplayName("Test pizza creation")
    void test_pizza_creation(){
        JavalinTest.test(testApp, (server, client) -> {
            Pizza postPizza = new Pizza(5, "Kebab", 220, 2);
            var response = client.post("/pizza", gson.toJson(postPizza));

            Assertions.assertEquals(201, response.code());
            Assertions.assertEquals(gson.toJson(postPizza), response.body().string());
            Assertions.assertEquals(4, testStorage.size());
        });
    }

}
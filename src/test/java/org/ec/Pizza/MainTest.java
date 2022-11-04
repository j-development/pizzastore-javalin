package org.ec.Pizza;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.ec.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MainTest {

    private final Gson gson = new Gson();

    HashMap<Integer, Pizza> testStorage = new HashMap<Integer, Pizza>();
    PizzaRepository testRepo = new InMemoryPizzaRepository(testStorage);
    PizzaService testService = new PizzaService(testRepo);
    PizzaController testController = new PizzaController(testService);

    @BeforeEach
    void resetStorage() {
        testStorage.clear();
        testStorage.put(1, new Pizza(1, "VESUVIO", 100, 1));
        testStorage.put(2, new Pizza(2, "CAPRICIOSSA", 100, 1));
        testStorage.put(3, new Pizza(3, "MARGARITA", 100, 1));
    }

    Javalin testApp = Main.createApp(testController);

    @Test
    @DisplayName("Get all pizzas")
    void test_get_all(){
        JavalinTest.test(testApp, (server, client) -> {
            var response = client.get("/pizza");

            ArrayList<Pizza> listFromDb = new ArrayList<>(testStorage.values());
            var jsonFromDb = gson.toJson(listFromDb);

            Assertions.assertEquals(200, response.code());
            Assertions.assertEquals(jsonFromDb, response.body().string());
        });
    }

}
package org.ec.Pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryPizzaRepository implements PizzaRepository{

    HashMap<Integer, Pizza> pizzaHashMap;

    public InMemoryPizzaRepository(HashMap<Integer, Pizza> pizzaHashMap) {
        this.pizzaHashMap = pizzaHashMap;
    }

    @Override
    public List<Pizza> findAll() {
        return new ArrayList<>(pizzaHashMap.values());
    }

    @Override
    public void add(Pizza pizza) {
        if(!pizzaHashMap.containsKey(pizza.getId())){
            pizzaHashMap.put(pizza.getId(), pizza);
        }
    }

    @Override
    public void deleteById(int id) {
        pizzaHashMap.remove(id);
    }

    @Override
    public Optional<Pizza> findById(int id) {
        return Optional.ofNullable(pizzaHashMap.get(id));
    }

    @Override
    public Pizza save(Pizza pizza) {
        pizzaHashMap.put(pizza.getId(), pizza);
        return pizza;
    }

    @Override
    public Boolean pizzaNameExists(String pizzaName) {
        for(Pizza value : pizzaHashMap.values()){
            if(value.getName().equals(pizzaName)){
                return true;
            }
        }
        return false;
    }
}

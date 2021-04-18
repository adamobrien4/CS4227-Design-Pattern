package main.memento;

import main.entities.BasketItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderMemento {

    private Map<String, BasketItem> basket = new HashMap<String, BasketItem>();

    public OrderMemento(Map<String, BasketItem> basket) {
        this.basket = basket;
    }

    public Map<String, BasketItem> getSavedArticle() {
        return basket;
    }

}

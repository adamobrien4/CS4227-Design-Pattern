package main.observer.listeners;

import main.entities.BasketItem;

public class BasketItemAddedListener implements EventListener {
    public BasketItemAddedListener() { }

    @Override
    public void update(String eventType, BasketItem item) {
        System.out.println(eventType + " : " + item);
    }
}

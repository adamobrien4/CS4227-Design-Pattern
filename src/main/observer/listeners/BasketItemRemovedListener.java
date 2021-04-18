package main.observer.listeners;

import main.entities.BasketItem;

public class BasketItemRemovedListener implements EventListener {
    public BasketItemRemovedListener() { }

    @Override
    public void update(String eventType, BasketItem item) {
        System.out.println(eventType + " : " + item);
    }
}

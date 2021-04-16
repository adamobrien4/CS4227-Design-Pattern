package main.observer.listeners;

import main.entities.BasketItem;

public class BasketItemAddedListener implements EventListener {

    public BasketItemAddedListener(BasketItem item) {

    }

    public BasketItemAddedListener() {

    }

    @Override
    public void update(String eventType) {
        System.out.println(eventType + ": notification.");
    }
}

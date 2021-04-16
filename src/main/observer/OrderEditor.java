package main.observer;

import main.entities.BasketItem;

public class OrderEditor {
    public EventManager events;

    public OrderEditor() {
        this.events = new EventManager("added", "removed");
    }

    public void addOrderItem(BasketItem item) {
        events.notify("added", item);
    }
}

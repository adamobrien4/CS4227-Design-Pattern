package main.memento;

import java.util.ArrayList;

public class OrderCaretaker {

    ArrayList<OrderMemento> savedArticles = new ArrayList<OrderMemento>();

    public void addMemento(OrderMemento m) {
        savedArticles.add(m);
        System.out.println(savedArticles);
    }

    public OrderMemento getMemento(int index) {
        return savedArticles.get(index);
    }
}

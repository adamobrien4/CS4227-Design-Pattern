package main.memento;

public class Originator {

    private String article;

    public void set(String newArticle) {
        System.out.println("From Originator: Current Version of Article\n" + newArticle);

        article = newArticle;
    }

    public OrderMemento storeInMemento() {
        System.out.println("From Originator: Saving to Memento");

        //return new OrderMemento(article);
        return null;
    }

    public String restoreFromMemento(OrderMemento orderMemento) {
        //article = orderMemento.getSavedArticle();

        System.out.println("From Originator: Previous Article Saved In Memento\n" + article);

        return article;
    }
}

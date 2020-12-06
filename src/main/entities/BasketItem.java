package main.entities;

public class BasketItem extends FoodItem {
    private int quantity;

    public BasketItem(String n, String[] a, double p){
        super(n, a, p);
        this.quantity = 1;
    }

    public BasketItem(String n,double p){
        super(n, p);
        this.quantity = 1;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public boolean decrementQuantity() {
        this.quantity--;
        if (quantity == 0) {
            return true;
        } 
        return false;
    }

    public static BasketItem fromFoodItem(FoodItem item) {
        if (item.hasAllergens()){
            return new BasketItem(item.getName(), item.getAllergens(), item.getPrice());
        }
        return new BasketItem(item.getName(), item.getPrice());
    }
}

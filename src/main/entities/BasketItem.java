package main.entities;
public class BasketItem extends FoodItem {
    private int quantity;

    public static class Builder extends FoodItem.Builder<Builder>{
        private int quantity = 1;

        public Builder(){}

        public Builder quantity(int quantity){
            this.quantity=quantity;
            return this;
        }
        
        public BasketItem build(){
            return new BasketItem(this);
        }


    }
        public BasketItem(Builder builder){

            super(builder);
            quantity= builder.quantity;
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
                return new BasketItem.Builder().name(item.getName()).price(item.getPrice()).allergens(item.getAllergens()).build();
            }
            return new BasketItem.Builder().name(item.getName()).price(item.getPrice()).build();
        }
    }

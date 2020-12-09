package main.entities;

import org.bson.Document;

public class Discount {
    
    private double amount;
    private String code;
    private int type;

    public static final int FOOD_DISCOUNT = 0;
    public static final int DELIVERY_DISCOUNT = 1;

    /*
        type: [
            0 : food discount
            1 : delivery discount
        ]
    */

    public Discount(double amt, String cd, int typ) {
        this.amount = amt;
        this.code = cd;
        this.type = typ;
    }

    public static Discount fromDocument(Document document) {
        return new Discount(document.getDouble("amount"), document.getString("code"), document.getInteger("type"));
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCode() {
        return this.code;
    }

    public int getType() {
        return this.type;
    }

}

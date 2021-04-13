package main.entities;
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


    public Discount() {}

    public Discount(double amt, String cd, int typ) {
        this.amount = amt;
        this.code = cd;
        this.type = typ;
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

    @Override
    public String toString() {
        return "Discount{" +
                "amount=" + amount +
                ", code='" + code + '\'' +
                ", type=" + type +
                '}';
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setType(int type) {
        this.type = type;
    }


}

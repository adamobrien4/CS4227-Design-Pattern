package main.entities.discount_visitor;

import java.util.ArrayList;

import main.entities.*;
import main.visitor.TaxVisitor;

public class DiscountVisitor implements Visitor {
    public DiscountVisitor() {
    }

    TaxVisitor tax = new TaxVisitor();

    @Override
    public String visitDiscount(Discount discount, FoodItem foodItem) {
        discount.setAmount(foodItem.acceptPrice(tax));
        discount.setCode("123");
        discount.setType(1);
        return null;
    }

}
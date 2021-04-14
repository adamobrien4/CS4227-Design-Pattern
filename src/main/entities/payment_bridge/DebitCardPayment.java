package main.entities.payment_bridge;

public class DebitCardPayment extends MakePayment {  
    @Override  
    public void makePayment() {  
        //payment object provides independency  
        payment.ProcessPayment("Debit Card Payment");  
    }  
}  
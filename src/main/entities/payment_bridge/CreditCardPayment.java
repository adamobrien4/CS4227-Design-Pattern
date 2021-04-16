package main.entities.payment_bridge;

//Refined Abstraction  
public class CreditCardPayment extends MakePayment {  
    @Override  
    public void makePayment() {  
        //payment object provides independency  
        payment.ProcessPayment("Credit Card Payment");  
    }  
}  
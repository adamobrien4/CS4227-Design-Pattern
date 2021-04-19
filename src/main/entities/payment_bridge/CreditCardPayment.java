package main.entities.payment_bridge;

//Refined Abstraction  
public class CreditCardPayment extends MakePayment {  
    @Override  
    public void makeCustomerPayment() {  
        
        //payment object provides independency  
        makePayment.ProcessPayment("Credit Card Payment");  
    }  
}  
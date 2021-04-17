package main.entities.payment_bridge;

//Abstraction  
public abstract class MakePayment {  
    public PaymentSystem makePayment; //instance
    
    String checkoutCardNumber;
    String cardExpiery;
    String checkoutCardOwner;
    String checkoutCvv;

    public abstract void makeCustomerPayment(); //method responsible to makePayment  
}  
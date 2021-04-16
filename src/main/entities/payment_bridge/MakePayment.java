package main.entities.payment_bridge;

//Abstraction  
public abstract class MakePayment {  
    PaymentSystem payment; //instance  
    public abstract void makePayment(); //method responsible to makePayment  
}  
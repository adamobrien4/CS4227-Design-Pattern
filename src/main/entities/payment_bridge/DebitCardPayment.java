package main.entities.payment_bridge;

public class DebitCardPayment extends MakePayment {  
   
    @Override  
    public void makeCustomerPayment() {  
        //payment object provides independency  
        makePayment.ProcessPayment("Debit Card Payment from DebitCardPyamnet Class");  
    }  
}  
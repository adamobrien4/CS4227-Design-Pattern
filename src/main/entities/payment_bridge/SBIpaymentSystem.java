package main.entities.payment_bridge;  
public class SBIpaymentSystem implements PaymentSystem { 
    

    public SBIpaymentSystem() {
    }
    
    @Override  
    public void ProcessPayment(String string) {  
        // TODO Auto-generated method stub  
        System.out.println("Using SBI payment gateway for " + string);  
    }  
    @Override  
    public void ProcessPayment() {  
        // TODO Auto-generated method stub  
        System.out.println("User will Pay on Delivery");  
    }  
}  
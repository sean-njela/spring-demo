package com.devopssean.spring_demo;

//import org.springframework.stereotype.Service;

// Commented out in favour of AppConfig
//@Service
public class BlikPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("BLIK PAYMENT: " + amount);
    }
}

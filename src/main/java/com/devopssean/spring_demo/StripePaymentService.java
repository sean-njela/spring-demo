package com.devopssean.spring_demo;

//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;

// Commented out in favour of AppConfig
//@Service
//@Primary
public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        // In the real world we would pass an argument obj that encapsulates info like CC info
        System.out.println("STRIPE PAYMENT: " + amount);
    }
}

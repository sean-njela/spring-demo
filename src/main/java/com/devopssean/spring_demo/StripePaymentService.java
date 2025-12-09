package com.devopssean.spring_demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Commented out in favor of AppConfig
//@Service
//@Primary
public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) { // In teh real world we would pass an object that encapsulates info like CC info
        System.out.println("STRIPE PAYMENT: " + amount);
    }
}

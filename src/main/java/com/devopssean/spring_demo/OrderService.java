package com.devopssean.spring_demo;

import org.springframework.stereotype.Service;

// Commented out in favor of AppConfig
//@Service
public class OrderService {
    private final PaymentService paymentService;

    // @Autowired Only if we have more than one constructor
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        // In the real world we pass an object
        paymentService.processPayment(500);
    }

//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
}

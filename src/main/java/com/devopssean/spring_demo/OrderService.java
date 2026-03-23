package com.devopssean.spring_demo;

//import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// Commented out in favour of AppConfig
//@Service
public class OrderService {
    private final PaymentService paymentService;

    // @Autowired Only if we have more than one constructor
    public OrderService(PaymentService paymentService) {
        // @Qualifier("paypal") can be inserted in the arguments along named @Service beans to have a default
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void init() {
        System.out.println("OrderService init");
    }

    public void placeOrder() {
        // In the real world we pass an object
        paymentService.processPayment(500);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("OrderService destroy");
    }

//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
}

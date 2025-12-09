package com.devopssean.spring_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    It is better to use this to configure which bean object gets injected into the application
    context over @Service annotations in the interface implementations. This allows us to decouple
    application configuration from the applications' code. This approach should be the default,
    with Service annotations being the go to for simpler solutions.
    This method enables us to employ conditional logic to determine which bean to utilize.
    The Spring Boot initialise
*/

@Configuration
public class AppConfig {
    /*
        In this class we create one or more methods for creating Beans.
        The name of the methods must be a verb.
    */

    @Value("${payment-gateway:blik}")
    private String paymentGateway;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService payPal() {
        return new PayPalPaymentService();
    }

    @Bean
    public PaymentService blik() {
        return new BlikPaymentService();
    }

    @Bean
    public OrderService orderService() {
        paymentGateway = paymentGateway.toLowerCase();

        if (paymentGateway.equals("paypal")) {
            return new OrderService(payPal());
        }
        if (paymentGateway.equals("stripe")) {
            return new OrderService(stripe());
        }
        return new OrderService(blik());
    }
}

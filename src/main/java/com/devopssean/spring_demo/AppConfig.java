package com.devopssean.spring_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

// It is better to use AppConfig to configure which bean object gets injected into the application
// context over @Service annotations in the interface implementations. This allows us to decouple
// application configuration from the applications' code. This approach should be the default,
// with Service annotations being the go to for simpler solutions.
// This method enables us to employ conditional logic to determine which bean to utilize.
// Spring boot initializes all Beans when the app starts. (Early initialization).
// We can also configure a lazy initialization if a certain bean consumes
// a large chunk of memory. In this case we use the @Lazy annotation.
// Beans have a @Scope annotation that determines how many times they are created:
//      Singleton: One instance.
//      Prototype: Bean created whenever an instance is called.
//      Request: Bean created for every http request. Lifecycle ends when session ends.
//      Session: Bean created for each http session. Lifecycle ends when session ends.
// If using the simple version, apply @Scope to the Manager class.
// We can manipulate Bean lifecycle behaviour using @PostConstruct and @PreDestroy annotations.

@Configuration
public class AppConfig {
    /*
        In this class we create one or more methods for creating Beans.
        The name of the methods must be a verb.
    */

    @Value("${payment-gateway:blik}")
    private String paymentGateway;

    @Bean
    @Lazy
    public PaymentService stripe() {
        System.out.println("Initializing Stripe");
        return new StripePaymentService();
    }

    @Bean
    @Scope("prototype")
    public PaymentService payPal() {
        System.out.println("Initializing PayPal");
        return new PayPalPaymentService();
    }

    @Bean
    public PaymentService blik() {
        System.out.println("Initializing Blik");
        return new BlikPaymentService();
    }

    @Bean
    public OrderService orderService() {
        paymentGateway = paymentGateway.toLowerCase();

        if (paymentGateway.equals("paypal")) {
            System.out.println("Injected PayPal");
            return new OrderService(payPal());
        }
        if (paymentGateway.equals("stripe")) {
            System.out.println("Injected Stripe");
            return new OrderService(stripe());
        }
        System.out.println("Injected Blik");
        return new OrderService(blik());
    }
}

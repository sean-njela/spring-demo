package com.devopssean.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
        // USING a setter (SHOULD ONLY BE FOR OPTIONAL DEPENDENCIES)
        // The constructor injection method above is the default way of passing objects
//        var orderService = new OrderService();
//        orderService.setPaymentService(new StripePaymentService());
//        orderService.placeOrder();

        // Using Beans and IOC
        ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
        var orderService2 = context.getBean(OrderService.class);
        orderService2.placeOrder();
	}
}

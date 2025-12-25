package com.devopssean.spring_demo;

import com.devopssean.spring_demo.entities.Address;
import com.devopssean.spring_demo.entities.Profile;
import com.devopssean.spring_demo.entities.Tag;
import com.devopssean.spring_demo.entities.User;
import com.devopssean.spring_demo.repositories.UserRepository;
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
		var repository = context.getBean(UserRepository.class);

		var orderService2 = context.getBean(OrderService.class);
        orderService2.placeOrder();

        // Running in the terminal (Comment out the above code)
        // Building DB object using builder pattern from lombok
        var user = User.builder()
			   	.name("Sean")
			   	.email("timba@sena.com")
			   	.password("12345")
			   	.build();

//	   	var address = Address.builder()
//				.city("Warsaw")
//			   	.street("juku street")
//			   	.zip("12345")
//			   	.build();
//
//	   	var profile = Profile.builder()
//			   	.bio("sean is a guy")
//			   	.phoneNumber("5555555")
//			   	.loyaltyPoints(255)
//			   	.build();

//	   	user.addAddress(address);
//	   	user.addTag("devops");
//	   	user.addProfile(profile);

//	   	repository.save(user); // This way we do not write any SQL statements

//		repository.findById(5L).orElseThrow();

		repository.findAll().forEach(u -> System.out.println(u.getEmail()));

//		repository.deleteById(5L);

	   	System.out.println(user.getEmail());
	}
}

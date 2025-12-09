package com.devopssean.spring_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Value("${spring.application.name:demoApp}") // using variables from settings file. Value after : is the default.
    private String appName;

    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;

    @RequestMapping("/")
    public String index()  { // we can name the function anything we want
        System.out.println("App Name: " + appName);
        System.out.println("Supported Currencies: " + supportedCurrencies);
        return "index.html";
    }
}

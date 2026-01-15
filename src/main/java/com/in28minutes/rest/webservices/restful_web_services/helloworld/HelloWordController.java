package com.in28minutes.rest.webservices.restful_web_services.helloworld;

import org.jspecify.annotations.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWordController {

    private MessageSource messageSource;

    public HelloWordController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";

    }

    @GetMapping(path = "/hello-world-bean")
    public helloWorldBean helloWorldBean(){
        return new helloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public helloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new helloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default message", locale);
    }


}
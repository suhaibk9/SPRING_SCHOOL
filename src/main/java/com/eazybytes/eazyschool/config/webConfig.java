package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class webConfig  implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
  //      registry.addViewController("/").setViewName("home");
        registry.addViewController("/courses").setViewName("courses");

//registry.addViewController("/holidays").setViewName("holidays");
        registry.addViewController("/about").setViewName("about");
    }
}

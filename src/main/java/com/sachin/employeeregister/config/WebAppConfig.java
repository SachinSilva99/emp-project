package com.sachin.employeeregister.config;

import com.sachin.employeeregister.WebInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebInitializer.class)
public class WebAppConfig {

}

package com.example.TravelinVServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
//@ComponentScan({"com.example.TravelinVServer","com.example.TravelinVServer.Service"})
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {UserDetailsService.class}))
public class TravelinVServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelinVServerApplication.class, args);
                
	}

}

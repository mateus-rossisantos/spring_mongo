package com.example.animais;

import com.example.animais.controller.AnimalController;
import com.example.animais.controller.AuthController;
import com.example.animais.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan
public class AnimaisApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimaisApplication.class, args);
	}

}

package com.julia.sets.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot class to scan for beans to set up for the war.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 *
 */
@SpringBootApplication
@ComponentScan("com.julia.sets")
@EnableJpaRepositories("com.julia.sets.data.repositories")
@EntityScan("com.julia.sets.data.entities")
public class SpringBootConfig extends SpringBootServletInitializer {

   /**
    * Initializes beans.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param args
    *           the application arguments (usually passed from a Java main method)
    * 
    * @see SpringApplication#run(Class, String[])
    */
   public static void main(String[] args) {

      SpringApplication.run(SpringBootConfig.class, args);
   }
}

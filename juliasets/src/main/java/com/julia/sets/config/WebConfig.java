package com.julia.sets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Config setup for ModelAndView mapping to jsp location. This also adds the
 * css, js, and scss file handlers.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param registry
    *           the {@link ResourceHandlerRegistry}
    *
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {

      // add GET handlers for css, js, and scss files
      registry.addResourceHandler("/css/**").addResourceLocations("/css/");
      registry.addResourceHandler("/js/**").addResourceLocations("/js/");
      registry.addResourceHandler("/scss/**").addResourceLocations("/scss/");
      registry.addResourceHandler("/js-min/**").addResourceLocations("/js-min/");
   }

   /**
    * Set up the {@link ViewResolver} for the app.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return {@link ViewResolver}
    */
   @Bean
   public ViewResolver viewResolver() {
      var bean = new InternalResourceViewResolver();

      bean.setViewClass(JstlView.class);
      bean.setPrefix("/WEB-INF/jsp/");
      bean.setSuffix(".jsp");

      return bean;
   }

}

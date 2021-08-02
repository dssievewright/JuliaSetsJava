package com.julia.sets.config;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.julia.sets.data.entities.converters.JuliaPictureToEntityConverter;

/**
 * This class is to set up a bean for {@link ModelMapper} for all this app's
 * needs.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Component
public class JuliaSetModelMapper extends ModelMapper {

   /**
    * add all mappings needed for this app
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    */
   public JuliaSetModelMapper() {
      super();
      addMappings(new JuliaPictureToEntityConverter());
   }
}

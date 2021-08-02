package com.julia.sets.data.entities.converters;

import java.awt.Color;
import java.util.SortedMap;
import java.util.TreeMap;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import com.julia.sets.data.entities.JuliaPictureEntity;
import com.julia.sets.presentation.complex.plane.JuliaPicture;

/**
 * Converter from {@link JuliaPicture} to {@link JuliaPictureEntity}.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class JuliaPictureToEntityConverter extends PropertyMap<JuliaPicture, JuliaPictureEntity> {

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   @Override
   protected void configure() {

      // converter for base colors from Color array to Map. This will eventually
      // become a JSON element in the SQL DB
      Converter<Color[], SortedMap<Integer, Integer>> toBaseColorsMap = new AbstractConverter<Color[], SortedMap<Integer, Integer>>() {

         protected SortedMap<Integer, Integer> convert(Color[] source) {
            var map = new TreeMap<Integer, Integer>();

            if (source == null) {
               return map;
            }

            for (var i = 0; i < source.length; i++) {
               map.put(i, source[i].getRGB());
            }
            return map;
         }
      };

      // map Julia Number to entity's columns
      map().setRealComponent(source.getJuliaNum().getRealComponent());
      map().setImaginaryComponent(source.getJuliaNum().getImaginaryComponent());

      // map the Julia Window properties
      map().setMinXValue(source.getJuliaWindow().getMinXValue());
      map().setMaxXValue(source.getJuliaWindow().getMaxXValue());
      map().setPictureWidth(source.getJuliaWindow().getPictureWidth());
      map().setMinYValue(source.getJuliaWindow().getMinYValue());
      map().setMaxYValue(source.getJuliaWindow().getMaxYValue());
      map().setPictureHeight(source.getJuliaWindow().getPictureHeight());

      // map all other fields in JuliaPicture
      // iterations and max modulus are mapped automatically
      map().setInteriorColor(source.getInteriorColor().getRGB());
      using(toBaseColorsMap).map(source.getColors()).setBaseColors(null);
   }
}

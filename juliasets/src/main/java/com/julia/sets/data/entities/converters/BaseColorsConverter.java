package com.julia.sets.data.entities.converters;

import java.util.SortedMap;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Converter class for the baseColors field from a Map in the entity to a JSON
 * object in the database.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Converter
@Component
public class BaseColorsConverter implements AttributeConverter<SortedMap<Integer, Integer>, String> {

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param attribute
    *           the baseColors to convert to JSON
    */
   @Override
   public String convertToDatabaseColumn(SortedMap<Integer, Integer> attribute) {

      var gson = new Gson();
      var gsonType = new TypeToken<SortedMap<Integer, Integer>>() {
      }.getType();

      return gson.toJson(attribute, gsonType);

   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return {@link SortedMap} conversion from dbData. Returns null if dbData is
    *         null or empty.
    */
   @Override
   public @Nullable SortedMap<Integer, Integer> convertToEntityAttribute(String dbData) {

      var gson = new Gson();
      var gsonType = new TypeToken<SortedMap<Integer, Integer>>() {
      }.getType();

      return gson.fromJson(dbData, gsonType);

   }

}

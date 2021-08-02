package com.julia.sets.data.repositories.helpers;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class for constructing {@link Predicate}s with a {@link Root} class T
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 * @param <T>
 *           the entity type corresponding with {@link Root}{@code <}T{@code >}
 */
public class PredicateConstructor<T> {

   Logger logger = LogManager.getLogger(PredicateConstructor.class);

   private T entity;

   private CriteriaBuilder cb;

   private Root<T> entityRoot;

   /**
    * Constructor for this class
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param entity
    *           the entity class
    * @param cb
    *           your favorite CriteriaBuilder
    * @param entityRoot
    *           a Root for the entity to be used in the Predicate
    */
   public PredicateConstructor(T entity, CriteriaBuilder cb, Root<T> entityRoot) {
      if (entity == null || cb == null || entityRoot == null) {
         throw new IllegalArgumentException("Cannot construct a PredicateConstructor with any null argument");
      }

      this.entity = entity;
      this.cb = cb;
      this.entityRoot = entityRoot;
   }

   /**
    * Check that this entity's field value and the table's field value are
    * equal.<br>
    * <br>
    * The SQL code generated will look like:<br>
    * <br>
    * {@code table.column_name = ?} <br>
    * <br>
    * where {@code entity.getFieldName()} is passed in as the unnamed parameter.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param fieldName
    *           the name of the field in the entity
    * @return a {@link Predicate} of the form
    *         {@code table.column_name = entity.getFieldName()}
    */
   public Predicate equality(String fieldName) {

      var getterName = new StringBuilder("get");
      getterName.append(fieldName.substring(0, 1).toUpperCase());
      getterName.append(fieldName.substring(1));

      Expression<Object> dbVal;
      Object entityVal;
      try {
         var m2 = entity.getClass().getMethod(getterName.toString());
         dbVal = entityRoot.get(fieldName);
         entityVal = m2.invoke(entity);

      } catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
         var msg = new StringBuilder("Error calling method with name = '");
         msg.append(getterName).append("'.");
         msg.append(" Check that the method exists and can be accessed.");

         logger.error(msg.toString(), e);
         throw new IllegalArgumentException("getter does not exist or cannot be accessed", e);

      } catch (IllegalArgumentException | InvocationTargetException e) {
         var msg = new StringBuilder("Error occurred when calling method with name = '");
         msg.append(getterName).append("'.");
         msg.append(" Check that this getter requires no parameters and that it is being called on");
         msg.append(" an instance of this object.");

         logger.error(msg.toString(), e);
         throw new IllegalArgumentException("calling getter threw exception", e);
      }

      return cb.equal(dbVal, entityVal);

   }
}

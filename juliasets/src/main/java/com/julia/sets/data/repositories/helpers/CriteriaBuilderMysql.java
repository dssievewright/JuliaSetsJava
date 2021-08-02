package com.julia.sets.data.repositories.helpers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An extension of hibernate's {@link CriteriaBuilderImpl} to account for more
 * SQL functions.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Component
public class CriteriaBuilderMysql extends CriteriaBuilderImpl {

   private static final long serialVersionUID = -8027406881910496105L;

   /**
    * Constructor for this class.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param emf
    *           the {@link EntityManagerFactory} set up for Spring/Hibernate.
    */
   @Autowired
   public CriteriaBuilderMysql(EntityManagerFactory emf) {
      super(emf.unwrap(SessionFactoryImpl.class));
   }

   /**
    * Method to call the JSON_CONTAINS function.<br>
    * <br>
    * SQL Equivalent: {@code JSON_CONTAINS(container, subset)}
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param container
    *           the first element to be passed into JSON_CONTAINS. MySql checks if
    *           this is the containing element.
    * @param subset
    *           the second element in the function call.
    * @return 1 if subset is contained in container and 0 otherwise.
    */
   public Expression<Integer> jsonContains(Expression<?> container, Expression<?> subset) {
      return function("JSON_CONTAINS", Integer.class, container, subset);
   }

   /**
    * Creates a {@link Predicate} for checking that the container does contain the
    * subset. <br>
    * <br>
    * SQL Equivalent: JSON_CONTAINS(container, subset) = 1
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param container
    *           the first element to be passed into JSON_CONTAINS. MySql checks if
    *           this is the containing element.
    * @param subset
    *           the second element in the function call.
    * @return the Predicate to be used as part of a where clause.
    */
   public Predicate jsonDoesContain(Expression<?> container, Expression<?> subset) {
      return equal(jsonContains(container, subset), 1);
   }

   /**
    * Creates a {@link Predicate} for checking that two values are equal as JSON
    * objects. <br>
    * <br>
    * SQL Equivalent: <br>
    * <br>
    * {@code JSON_CONTAINS(x, y) = 1} <span style="color:green">
    * {@code --check that all elements in y are in x}</span><br>
    * {@code AND JSON_CONTAINS(y, x) = 1} <span style="color:green">
    * {@code --check that all elements in x are in y}</span><br>
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param x First JSON object
    * @param y Second JSON object
    * @return
    */
   public Predicate jsonEquals(Expression<?> x, Expression<?> y) {
      return and(jsonDoesContain(x, y), jsonDoesContain(y, x));
   }

}

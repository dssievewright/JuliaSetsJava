package com.julia.sets.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.julia.sets.data.entities.JuliaPictureEntity;
import com.julia.sets.data.entities.converters.BaseColorsConverter;
import com.julia.sets.data.repositories.helpers.CriteriaBuilderMysql;
import com.julia.sets.data.repositories.helpers.PredicateConstructor;

/**
 * Repo implementation used for the julia_picture entity
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class JuliaPictureRepositoryImpl implements JuliaPictureRepository {

   @PersistenceContext
   private EntityManager em;

   @Autowired
   protected BaseColorsConverter baseColorsConverter;

   @Autowired
   private CriteriaBuilderMysql cbm;

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   @Override
   public List<Integer> getIdsByEntity(JuliaPictureEntity jpe) {

      // specify returned entity of query
      var criteriaQuery = cbm.createQuery(Integer.class);

      // Set up FROM clause
      var jpeRoot = criteriaQuery.from(JuliaPictureEntity.class);

      // add WHERE clause
      var whereClause = buildWhereClause(jpe, jpeRoot);
      criteriaQuery = criteriaQuery.where(whereClause);

      // add SELECT clause
      criteriaQuery.select(jpeRoot.get("id"));

      // run query
      var query = em.createQuery(criteriaQuery);
      return query.getResultList();
   }

   /**
    * Helper method to build Specification for
    * {@link #getIdsByEntity(JuliaPictureEntity)}
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jpe  the {@link JuliaPictureEntity} to search for
    * @param jpeRoot a {@link Root} for the table referenced in the where clause
    * @return
    */
   private Predicate buildWhereClause(JuliaPictureEntity jpe, Root<JuliaPictureEntity> jpeRoot) {

      // initialize a predicate constructor
      var pc = new PredicateConstructor<JuliaPictureEntity>(jpe, cbm, jpeRoot);

      // initialize the where clause list.
      List<Predicate> whereClause = new ArrayList<>();

      // add conditions of the form
      // julia_picture.field_name = :entity.fieldName
      whereClause.add(pc.equality("imaginaryComponent"));
      whereClause.add(pc.equality("realComponent"));
      whereClause.add(pc.equality("interiorColor"));
      whereClause.add(pc.equality("iterations"));
      whereClause.add(pc.equality("maxModulus"));
      whereClause.add(pc.equality("minXValue"));
      whereClause.add(pc.equality("maxXValue"));
      whereClause.add(pc.equality("minYValue"));
      whereClause.add(pc.equality("maxYValue"));
      whereClause.add(pc.equality("pictureHeight"));
      whereClause.add(pc.equality("pictureWidth"));

      // set up for base_colors field
      var testColors = jpe.getBaseColors();
      var dbValue = jpeRoot.get("baseColors");

      if (testColors != null) {
         // convert the map to JSON and then to an expression for the criteria builder
         var testJson = baseColorsConverter.convertToDatabaseColumn(testColors);
         var testValue = cbm.literal(testJson);

         whereClause.add(cbm.jsonEquals(dbValue, testValue));
      } else {
         // if this entity has null base colors, check that db record also has null value
         whereClause.add(cbm.isNull(dbValue));
      }

      // Combine all these partial conditions together in one
      // WHERE clause and make sure all conditions are met
      return cbm.and(whereClause.toArray(new Predicate[0]));
   }

}

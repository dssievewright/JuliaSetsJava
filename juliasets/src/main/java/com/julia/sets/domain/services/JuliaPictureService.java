package com.julia.sets.domain.services;

import java.util.List;

import com.julia.sets.data.entities.JuliaPictureEntity;

/**
 * Service for queries related to {@link JuliaPictureEntity}
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public interface JuliaPictureService {

   /**
    * This will search for all records that match the jpe and returns their list of
    * ids.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jpe
    *           the {@link JuliaPictureEntity} to search for
    * @return a {@link List}{@code <}{@link Integer}{@code >} that contains the id
    *         of all matching records in julia_picture.
    */
   public List<Integer> getIds(JuliaPictureEntity jpe);

   /**
    * Inserts or updates the record in julia_picture.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jpe
    *           the {@link JuliaPictureEntity} to save
    * @return the saved {@link JuliaPictureEntity}. This may be a new instance of
    *         the object.
    */
   public JuliaPictureEntity save(JuliaPictureEntity jpe);
}

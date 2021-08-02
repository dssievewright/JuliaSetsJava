package com.julia.sets.data.repositories;

import java.util.List;

import com.julia.sets.data.entities.JuliaPictureEntity;

/**
 * Repo interface for special methods related to querying julia_picture table
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public interface JuliaPictureRepository {

   /**
    * Searches the table for any records that match all non-id fields in the given
    * {@link JuliaPictureEntity}
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jpe
    *           record to search for
    * @return all ids in the table that match the non-id fields in jpe
    */
   List<Integer> getIdsByEntity(JuliaPictureEntity jpe);

}

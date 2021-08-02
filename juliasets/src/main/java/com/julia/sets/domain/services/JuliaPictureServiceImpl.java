package com.julia.sets.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.sets.data.entities.JuliaPictureEntity;
import com.julia.sets.data.repositories.JuliaPictureJpaRepository;

/**
 * Implementation of {@link JuliaPictureService}
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Service
public class JuliaPictureServiceImpl implements JuliaPictureService {

   @Autowired
   private JuliaPictureJpaRepository juliaPictureRepository;

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public List<Integer> getIds(JuliaPictureEntity jpe) {
      return juliaPictureRepository.getIdsByEntity(jpe);
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public JuliaPictureEntity save(JuliaPictureEntity jpe) {
      return juliaPictureRepository.save(jpe);
   }
}

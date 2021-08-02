package com.julia.sets.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julia.sets.data.entities.JuliaPictureEntity;

/**
 * Repo interface for JPA. Mapped to an implementation in
 * {@link com.julia.sets.config.SpringBootConfig}
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface JuliaPictureJpaRepository
      extends JpaRepository<JuliaPictureEntity, Integer>, JuliaPictureRepository {

}

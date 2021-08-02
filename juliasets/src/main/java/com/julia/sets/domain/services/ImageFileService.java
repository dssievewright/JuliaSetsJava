package com.julia.sets.domain.services;

import java.util.List;

import com.julia.sets.data.entities.JuliaPictureEntity;
import com.julia.sets.presentation.complex.plane.JuliaPicture;

/**
 * Interface to handle requests for image files
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public interface ImageFileService {

   /**
    * Checks if any of the ids associated with a {@link JuliaPicture} has an
    * existing image saved on the server. This returns the first file name found.
    * If no file was found, an empty string is returned.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param ids
    *           the list of ids to look for
    * @return a file name if the image exists. An empty string is returned if no
    *         file was found.
    */
   public String getFileName(List<Integer> ids);

   /**
    * Creates an image based on the {@link JuliaPicture}
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jp
    *           the parameters for the Julia Set image to generate
    * @param pictureId
    *           the id corresponding with the associated {@link JuliaPictureEntity}
    * @return the file name for the image generated.
    * @throws ImageFileServiceException
    *            if a critical system error occurs during the creation of the image
    *            such as {@link InterruptedException} or {@link IOException}
    */
   public String createImage(JuliaPicture jp, Integer pictureId) throws ImageFileServiceException;
}

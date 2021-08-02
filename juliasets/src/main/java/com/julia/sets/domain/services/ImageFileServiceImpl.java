package com.julia.sets.domain.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.sets.config.ApplicationProperties;
import com.julia.sets.config.ImageFileFilter;
import com.julia.sets.domain.matrix.JuliaSetMatrixCalculator;
import com.julia.sets.presentation.complex.plane.JuliaPicture;

/**
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Service
public class ImageFileServiceImpl implements ImageFileService {

   Logger logger = LogManager.getLogger(ImageFileServiceImpl.class);

   @Autowired
   private ApplicationProperties props;

   @Autowired
   private JuliaSetMatrixCalculator jsmc;

   /**
    * Searches the database to see if the Julia Picture has ever been attempted to
    * be made before. If so, this will search to see if the image file still exists
    * on the server and return the file name.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   @Override
   public String getFileName(List<Integer> ids) {
      var answer = "";

      // if an id was returned, check if an appropriate image file exists.
      if (ids != null && !ids.isEmpty()) {
         // check if this id corresponds with an image
         var folder = props.getImageFolder();
         var filter = new ImageFileFilter(ids);
         var files = folder.listFiles(filter);
         if (files != null && files.length > 0) {
            answer = files[0].getName();
         }
      }
      return answer;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   @Override
   public String createImage(JuliaPicture jp, Integer pictureId) throws ImageFileServiceException {

      try {
         // get the matrix used to generate the image
         Integer[][] m = jsmc.fillMatrix(jp);

         // set up the image
         var image = new BufferedImage(m[0].length, m.length, BufferedImage.TYPE_INT_ARGB);

         // convert the matix's values to the desired colors
         List<Color> colorPalette = jp.getFilledColorPalette();
         for (var i = 0; i < m.length; i++) {
            for (var j = 0; j < m[i].length; j++) {
               int a = m[i][j];

               // initialize next color as the desired color for points inside the Julia set.
               // Points outside the Julia set will have a matrix value between 1 and cp.size()
               // and be assigned their color from the palette.
               var nextColor = jp.getInteriorColor();
               if (1 <= a && a <= colorPalette.size()) {
                  nextColor = colorPalette.get(a - 1);
               }
               image.setRGB(j, i, nextColor.getRGB());
            }
         }

         // save the image to the correct location
         var imageFolder = props.getImageFolder();
         var fileName = pictureId.toString() + ".png";
         var output = new File(imageFolder, fileName);
         ImageIO.write(image, "png", output);
         return fileName;
      } catch (InterruptedException e) {
         logger.error("Thread Interrupted Exception while filling matrix", e);
         Thread.currentThread().interrupt();
         throw new ImageFileServiceException(ImageFileServiceException.MATRIX_THREAD_INTERRUPTED, e);
      } catch (IOException e) {
         logger.error("IO Exception while writing image to output file", e);
         throw new ImageFileServiceException(ImageFileServiceException.IMAGE_IO_EXCEPTION, e);
      }
   }

}

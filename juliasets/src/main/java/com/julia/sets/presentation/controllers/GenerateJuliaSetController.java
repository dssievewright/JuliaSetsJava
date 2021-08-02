package com.julia.sets.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.julia.sets.config.ApplicationProperties;
import com.julia.sets.config.JuliaSetModelMapper;
import com.julia.sets.data.entities.JuliaPictureEntity;
import com.julia.sets.domain.services.ImageFileService;
import com.julia.sets.domain.services.ImageFileServiceException;
import com.julia.sets.domain.services.JuliaPictureService;
import com.julia.sets.presentation.complex.plane.Complex;
import com.julia.sets.presentation.complex.plane.JuliaPicture;
import com.julia.sets.presentation.complex.plane.JuliaWindow;

/**
 * Controller called when requests are made to generate a Julia Set image
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Controller
public class GenerateJuliaSetController {

   @Autowired
   private JuliaPictureService juliaPictureService;

   @Autowired
   private JuliaSetModelMapper juliaSetModelMapper;

   @Autowired
   private ImageFileService imageFileService;

   @Autowired
   private ApplicationProperties properties;

   /**
    * Method to get default min and max values for client side checking
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return
    */
   @GetMapping("getConstraints")
   @ResponseBody
   public String getConstraints() {
      var constraints = new JsonObject();

      // add sql limits
      constraints.addProperty("sqlIntMin", properties.getSqlIntMin());
      constraints.addProperty("sqlIntMax", properties.getSqlIntMax());
      constraints.addProperty("sqlDecLength", properties.getSqlDecLength());
      constraints.addProperty("sqlDecPrecision", properties.getSqlDecPrecision());

      // add julia picture and window limits
      constraints.addProperty("iterationsLimit", JuliaPicture.ITERATIONS_UPPER_BOUND);
      constraints.addProperty("modulusLimit", JuliaPicture.MAX_MODULUS_UPPER_BOUND);
      constraints.addProperty("resolutionLimit", JuliaWindow.RESOLUTION_UPPER_BOUND);

      return constraints.toString();
   }

   
   //TODO: update javadoc for next method (added params)
   
   /**
    * Either finds an existing image with the same parameters or generates a new
    * Julia set image.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param realComponent
    *           real component of the complex number for the Julia Function
    * @param imaginaryComponent
    *           imaginary component of the complex number for the Julia Function
    * @return {@link ModelAndView} to display the Julia Set picture
    * @throws ImageFileServiceException
    *            if any exception occurs while generating a new Julia set image
    */
   @GetMapping("/generateJuliaSet.html")
   @ResponseBody
   public ModelAndView getJuliaSetWithDefaults(@RequestParam double realComponent,
         @RequestParam double imaginaryComponent, @RequestParam double minXValue,
         @RequestParam double maxXValue, @RequestParam double minYValue, @RequestParam double maxYValue,
         @RequestParam int pictureWidth, @RequestParam int pictureHeight, @RequestParam int iterations,
         @RequestParam int maxModulus)
         throws ImageFileServiceException {

      // TODO: validate JuliaPicture parameters

      
      // create the JuliaPicture based on passed in parameters
      var juliaWindow = new JuliaWindow(minXValue, maxXValue, pictureWidth, minYValue, maxYValue, pictureHeight);
      var juliaNum =  new Complex(realComponent, imaginaryComponent);
      var colors = JuliaPicture.getDefaultColors();
      var interiorColor = JuliaPicture.DEFAULT_INTERIOR_COLOR;
      var juliaPicture = new JuliaPicture(juliaNum, iterations, maxModulus, juliaWindow, colors, interiorColor);
      
      
      // create the entity processor
      var jpeProcessor = new JuliaPictureEntityProcessor(juliaPicture);

      // get the fileName from the processor and create a new file if none exists.
      var fileName = jpeProcessor.getFileName();

      var mav = new ModelAndView("juliaSetImage");
      mav.addObject("fileName", fileName);

      return mav;
   }

   /**
    * Processor class for the Julia Set image.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    */
   private class JuliaPictureEntityProcessor {

      private JuliaPicture jp;

      private JuliaPictureEntity jpe;

      private List<Integer> ids;

      private String fileName;

      /**
       * Constructor - determines the entity, potential matches in the DB, and
       * corresponding files on the server.
       * 
       * @author Dan Sievewright
       * @version 1.0.0
       * @since 1.0.0
       *
       * @param jp
       *           the {@link JuliaPicture} we wish to find an image for
       */
      public JuliaPictureEntityProcessor(JuliaPicture jp) {
         this.jp = jp;
         jpe = juliaSetModelMapper.map(jp, JuliaPictureEntity.class);
         setIds(juliaPictureService.getIds(jpe));
         setFileName(imageFileService.getFileName(ids));
      }

      /**
       * Sets the ids and does a null check to initialize.
       * 
       * @author Dan Sievewright
       * @version 1.0.0
       * @since 1.0.0
       *
       * @param ids
       *           the ids to set
       */
      private void setIds(List<Integer> ids) {
         this.ids = (ids == null) ? new ArrayList<>() : ids;
      }

      /**
       * Sets the fileName and initializes to an empty string if null
       * 
       * @author Dan Sievewright
       * @version 1.0.0
       * @since 1.0.0
       *
       * @param fileName
       *           the fileName to set
       */
      private void setFileName(String fileName) {
         this.fileName = (fileName == null) ? "" : fileName;
      }

      /**
       * Helper method to be called for {@link #getFileName()} to determine if an
       * existing file exists.
       * 
       * @author Dan Sievewright
       * @version 1.0.0
       * @since 1.0.0
       *
       * @return
       */
      private Integer getId() {
         // file names are identified by id, i.e., 1.png, 2.png, etc.
         var fileId = "";
         if (fileName.contains(".")) {
            fileId = fileName.substring(0, fileName.indexOf("."));
         }
         for (var id : ids) {
            jpe.setId(id);
            if (id.toString().equals(fileId)) {
               // exit if we have an id corresponding to a file
               return jpe.getId();
            }
         }

         // If no ids, then create a new record
         if (jpe.getId() == null) {
            // reassign back to jpe in case a new instance is created.
            jpe = juliaPictureService.save(jpe);
         }

         return jpe.getId();
      }

      /**
       * Method to get the file name for the Julia Set image. If one doesn't exist, a
       * new picture is created and its file name is returned.
       * 
       * @author Dan Sievewright
       * @version 1.0.0
       * @since 1.0.0
       *
       * @return the fileName
       * @throws ImageFileServiceException
       *            if creating the file resulted in a server error
       */
      public String getFileName() throws ImageFileServiceException {
         var id = getId();
         if (fileName.isEmpty()) {
            fileName = imageFileService.createImage(jp, id);
         }
         return fileName;
      }
   }
}

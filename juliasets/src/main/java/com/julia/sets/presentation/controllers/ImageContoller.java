package com.julia.sets.presentation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.julia.sets.config.ApplicationProperties;

/**
 * This handles all requests for images for Julia Sets. Files are saved to the
 * imageFolder property set in juliaSet.properties.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Controller
public class ImageContoller {

   @Autowired
   private ApplicationProperties applicationProperties;

   /**
    * Handles requests for images and grabs the desired file from within the
    * defined imageFolder.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param imageName
    *           name of the requested file
    * @return {@link ResponseEntity} to show the image
    * @throws IOException
    *            if an I/O error occurs
    */
   @GetMapping(value = "/images/{imageName}")
   public @ResponseBody ResponseEntity<InputStreamResource> getImageFile(
         @PathVariable("imageName") String imageName)
         throws IOException {

      // get output file
      File imageFolder = applicationProperties.getImageFolder();
      var output = new File(imageFolder, imageName);

      if (!output.exists()) {
         throw new FileNotFoundException("The requested image, '" + imageName + "', does not exist.");
      }

      // get MediaType
      var contentType = Files.probeContentType(output.toPath());
      var mediaType = MediaType.parseMediaType(contentType);

      // get Stream
      var isr = new InputStreamResource(new FileInputStream(output));

      return ResponseEntity.ok().contentLength(output.length()).contentType(mediaType).body(isr);
   }
   
   /**
    * Handles requests for images and grabs the desired file from within the
    * defined imageFolder.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param imageName
    *           name of the requested file
    * @return {@link ResponseEntity} to show the image
    * @throws IOException
    *            if an I/O error occurs
    */
   @GetMapping(value = "/tex/{imageName}")
   public @ResponseBody ResponseEntity<InputStreamResource> getTexFile(
         @PathVariable("imageName") String imageName)
         throws IOException {

      // get output file
      File imageFolder = applicationProperties.getTexFolder();
      var output = new File(imageFolder, imageName);

      if (!output.exists()) {
         throw new FileNotFoundException("The requested image, '" + imageName + "', does not exist.");
      }

      // get MediaType
      var contentType = Files.probeContentType(output.toPath());
      var mediaType = MediaType.parseMediaType(contentType);

      // get Stream
      var isr = new InputStreamResource(new FileInputStream(output));

      return ResponseEntity.ok().contentLength(output.length()).contentType(mediaType).body(isr);
   }
   
   //TODO: refactor above methods
}

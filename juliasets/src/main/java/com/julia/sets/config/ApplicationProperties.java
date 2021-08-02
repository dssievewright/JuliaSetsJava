package com.julia.sets.config;

import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Contains the mappings from the properties file in src/main/resources as well
 * as some commonly used properties for the controllers.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@PropertySource("classpath:juliaSetApplication.properties")
public class ApplicationProperties {

   @Value("${imageFolderUri}")
   private String imageFolderUri;
   
   @Value("${texFolderUri}")
   private String texFolderUri;

   @Value("${maxWaitTime}")
   private Integer maxWaitTime;

   @Value("${maxThreads}")
   private Integer maxThreads;

   @Value("${sql.int.min}")
   private BigInteger sqlIntMin;

   @Value("${sql.int.max}")
   private BigInteger sqlIntMax;

   @Value("${sql.dec.length}")
   private BigInteger sqlDecLength;

   @Value("${sql.dec.precision}")
   private BigInteger sqlDecPrecision;

   private File imageFolder;
   
   private File texFolder;

   /**
    * Empty constructor for bean initialization
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public ApplicationProperties() {
      super();
   }

   /**
    * Create the commonly used objects for this application. This is done as
    * {@link PostConstruct} so that referenced fields from properties file can be
    * referenced after injection.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @throws URISyntaxException
    *            if imageFolderUri is not setup correctly in
    *            juliaSetApplication.properties
    */
   @PostConstruct
   public void setAllProperties() throws URISyntaxException {

      imageFolder = new File(new URI(imageFolderUri));
      texFolder = new File(new URI(texFolderUri));
   }

   /**
    * Get the folder (as a {@link File} object) that contains the Julia Set
    * pictures that have been generated.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the {@link File} that contains all the images for this application
    */
   public File getImageFolder() {
      return imageFolder;
   }
   
   /**
    * Get the folder (as a {@link File} object) that contains the images generated from tex.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the {@link File} that contains all the tex images for this application
    */
   public File getTexFolder() {
      return texFolder;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the maxWaitTime
    */
   public Integer getMaxWaitTime() {
      return maxWaitTime;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the maxThreads
    */
   public Integer getMaxThreads() {
      return maxThreads;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the sqlIntMin
    */
   public BigInteger getSqlIntMin() {
      return sqlIntMin;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the sqlIntMax
    */
   public BigInteger getSqlIntMax() {
      return sqlIntMax;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the sqlDecLength
    */
   public BigInteger getSqlDecLength() {
      return sqlDecLength;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the sqlDecPrecision
    */
   public BigInteger getSqlDecPrecision() {
      return sqlDecPrecision;
   }

}

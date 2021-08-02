package com.julia.sets.config;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * File filter to look for potential Julia Set image files corresponding with
 * saved ids in the database.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class ImageFileFilter implements FileFilter {

   private static final String OK_FILE_EXTENSIONS = "((jpg)|(jpeg)|(png)|(gif))";

   private final String regex;

   /**
    * Constructor for this filter
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param potentialNames
    *           list of names to search for
    */
   public ImageFileFilter(List<Integer> potentialNames) {
      super();

      var nameRegex = new StringBuilder("((");

      var firstElement = true;

      for (var id : potentialNames) {
         if (firstElement) {
            firstElement = false;
            nameRegex.append(id);
         } else {

            nameRegex.append(")|(").append(id.toString());
         }
      }
      nameRegex.append("))");

      regex = nameRegex.append("\\.").append(OK_FILE_EXTENSIONS).toString();
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param file
    *           the {@link File} that to check for this filter.
    * @return true if file meets filter criteria
    */
   @Override
   public boolean accept(File file) {
      return file.getName().toLowerCase().matches(regex);
   }

}

package com.julia.sets.domain.services;

/**
 * Exception thrown for system errors when creating an image.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class ImageFileServiceException extends Exception {

   private static final long serialVersionUID = 3135877859356042883L;

   /**
    * Error message for interrupted threads
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String MATRIX_THREAD_INTERRUPTED = "Thread Interrupted Exception while filling matrix for Julia Picture";

   /**
    * Error message for IO Exceptions
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String IMAGE_IO_EXCEPTION = "IO Exception while writing Julia Set image to output file";

   /**
    * Creates an exception with the desired error message.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param errorMessage
    *           the error message
    */
   public ImageFileServiceException(String errorMessage) {
      super(errorMessage);
   }

   /**
    * Creates an exception with the error message and an attached exception
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param errorMessage
    *           the error message
    * @param err
    *           the exception that occurred.
    */
   public ImageFileServiceException(String errorMessage, Throwable err) {
      super(errorMessage, err);
   }
}

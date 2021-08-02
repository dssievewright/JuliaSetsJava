package com.julia.sets.presentation.complex.plane;

import com.julia.sets.domain.services.ImageFileServiceException;

/**
 * Exception class to represent issues when fields are set in an invalid way.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class JuliaException extends ImageFileServiceException {

   private static final long serialVersionUID = 1416326824181614240L;

   /**
    * This message is used when invalid values are attempted to be set along the
    * real axis for a Julia Set image.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String POSITIVE_WIDTH_REQ = "Window Size must have positive width";

   /**
    * This message is used when invalid values are attempted to be set along the
    * imaginary axis for a Julia Set image.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String POSITIVE_HEIGHT_REQ = "Window Size must have positive height";

   /**
    * This message is used to indicate that a {@link JuliaWindow} is null to
    * preempt a {@link NullPointerException}.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String NULL_JULIA_WINDOW = "JuliaWindow object is null";

   /**
    * This message is used to indicate that the {@link Complex} value used for a
    * Julia Number has not been initialized.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String NULL_JULIA_NUMBER = "The Julia Number cannot be null";
   
   /**
    * Message to show if an invalid value is used for iterations.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String NONNEGATIVE_ITERATIONS = "The number of iterations must be positive";

   /**
    * Create an exception with the desired error message.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param errorMessage
    *           a description of why the error occurred.
    */
   public JuliaException(String errorMessage) {
      super(errorMessage);
   }

   /**
    * Create an exception with the desired error message when another error occurs
    * and is trapped.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param errorMessage
    *           a more specific description of why an error occurred.
    * @param err
    *           the {@link Throwable} that caused the original error to be used for
    *           further debugging
    */
   public JuliaException(String errorMessage, Throwable err) {
      super(errorMessage, err);
   }
}

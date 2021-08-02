package com.julia.sets.presentation.colors;

import com.julia.sets.domain.services.ImageFileServiceException;

/**
 * Exception class for issues with creating color palettes.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class ColorPaletteException extends ImageFileServiceException {

   private static final long serialVersionUID = 270654667108912055L;

   /**
    * Message when an invalid set of base colors for the colorPalette is used
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String INVALID_COLORS = "At least one color used in the color palette is not defined";

   /**
    * Message when an invalid size for the colorPalette is used
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final String INVALID_PALETTE_SIZE = "palette size must be positive";

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
   public ColorPaletteException(String errorMessage) {
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
   public ColorPaletteException(String errorMessage, Throwable err) {
      super(errorMessage, err);
   }
}

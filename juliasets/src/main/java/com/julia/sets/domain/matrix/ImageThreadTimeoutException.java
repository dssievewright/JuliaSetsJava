package com.julia.sets.domain.matrix;

import com.julia.sets.domain.services.ImageFileServiceException;

public class ImageThreadTimeoutException extends ImageFileServiceException {

   private static final long serialVersionUID = -3122454318527494019L;

   public ImageThreadTimeoutException(String errorMessage) {
      super(errorMessage);
   }

   public ImageThreadTimeoutException(String errorMessage, Throwable err) {
      super(errorMessage, err);
   }
}
